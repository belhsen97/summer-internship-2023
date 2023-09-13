package tn.esprit.Services;

import tn.esprit.Dtos.AuthenticationRequestDto;
import tn.esprit.Dtos.AuthenticationResponseDto;
import tn.esprit.Dtos.MsgReponseStatusDto;
import tn.esprit.Entitys.Roles;

public interface IAuthenticationService {
    AuthenticationResponseDto authenticate(AuthenticationRequestDto request) throws Exception;
}
