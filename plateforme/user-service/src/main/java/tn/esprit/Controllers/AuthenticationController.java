package tn.esprit.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import tn.esprit.Dtos.AuthenticationRequestDto;
import tn.esprit.Dtos.AuthenticationResponseDto;
import tn.esprit.Dtos.MsgReponseStatusDto;
import tn.esprit.Dtos.ReponseStatus;
import tn.esprit.Entitys.Roles;
import tn.esprit.Services.IAuthenticationService;

@Controller
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

  private  IAuthenticationService iauthenticationService;
  @Autowired
  public AuthenticationController(@Qualifier("authentication-service") IAuthenticationService iauthenticationService){this.iauthenticationService = iauthenticationService;}


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDto> authenticate( @RequestBody AuthenticationRequestDto request) throws Exception {
        return ResponseEntity.ok(iauthenticationService.authenticate(request));}
}
