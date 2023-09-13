package tn.esprit.Services;

import tn.esprit.Dtos.AuthenticationRequestDto;
import tn.esprit.Dtos.MsgReponseStatusDto;
import tn.esprit.Entitys.Roles;
import tn.esprit.Entitys.User;
import tn.esprit.Libs.IGenericCRUD;

import java.io.IOException;

public interface IUserService extends IGenericCRUD<User,Long> {
    MsgReponseStatusDto register(AuthenticationRequestDto request)throws IOException, InterruptedException;
    MsgReponseStatusDto confirmEmail(String username , String code);
    MsgReponseStatusDto sendMailCodeForgotPassword(String username , String email) throws IOException, InterruptedException;
    MsgReponseStatusDto updateForgotPassword(String code, String newPassword);
    MsgReponseStatusDto updatePassword(String usename,String currentPassword, String newPassword);
    MsgReponseStatusDto updateRoleAndActivate(String username , Roles role, boolean enabled);
}

