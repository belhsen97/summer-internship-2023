package tn.esprit.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.Configures.MyConfigInitParameters;
import tn.esprit.Dtos.AuthenticationRequestDto;
import tn.esprit.Dtos.AuthenticationResponseDto;
import tn.esprit.Dtos.MsgReponseStatusDto;
import tn.esprit.Dtos.ReponseStatus;
import tn.esprit.Entitys.*;
import tn.esprit.Mappers.AccountMapper;
import tn.esprit.Repositorys.AccountRepository;
import tn.esprit.Repositorys.TokenRepository;
import tn.esprit.Repositorys.UserRepository;
import tn.esprit.Securitys.JwtService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service("authentication-service")
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    @Transactional
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));
        User user = userRepository.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new Exception("Error findByEmail"));
        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return   AuthenticationResponseDto.childBuilder()
                .token(jwtToken)
                .title("Authentication")
                .datestamp(LocalDate.now())
                .timestamp(LocalTime.now())
                .status(ReponseStatus.SUCCESSFUL)
                .message("Successful to access account")
                .build();
    }
    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }


}













