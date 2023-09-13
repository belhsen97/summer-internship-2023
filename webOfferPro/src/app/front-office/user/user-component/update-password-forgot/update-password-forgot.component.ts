 
import { Component,OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { ActivatedRoute } from '@angular/router';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-update-password-forgot',
  templateUrl: './update-password-forgot.component.html',
  styleUrls: ['./update-password-forgot.component.css']
})
export class UpdatePasswordForgotComponent implements OnInit {

public newPassword! : string;
public valodatePassword! : string;
private code! : string;
isNotSamePassword : boolean = false;

  constructor( public authService : AuthenticationService , private route: ActivatedRoute ) {
    this.code = this.route.snapshot.params['code'];
     console.log( this.code );
     this.authService.msgReponseStatusDto =  
     { datestamp: new Date() , timestamp: new Date()
      , status : ReponseStatus.ERROR ,
      message : "Your mail is correct so you see your email for complete next step",
      title : "Error",} }

  ngOnInit(): void {}
  validator():void{if (this.newPassword != this.valodatePassword){this.isNotSamePassword = true; }
    else {this.isNotSamePassword = false; } }

  resetPassword(form: NgForm):void {   console.log(form.invalid);  /*this.stateMsgBoxAuth = true;*/ }


  stateMsgBoxAuth : boolean = false;
  closeEventstateMsgBoxAuth($event:any):void {   this.stateMsgBoxAuth = $event; }









 

  signIn():void {this.authService.goToComponent('sign-in');  }
}
