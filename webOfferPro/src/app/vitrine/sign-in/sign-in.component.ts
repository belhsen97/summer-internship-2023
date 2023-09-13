import { Component,OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';  
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  stateMsgBoxAuth : boolean = false;
  constructor( public authService : AuthenticationService  ) { }
  ngOnInit(): void {}
  signIn(form: NgForm):void {  
      /*this.stateMsgBoxAuth = true;*/
     if(!form.invalid){
      this.authService.login(this.authService.authRequestDto).subscribe(
        (response) => { 
          this.authService.authResponseDto = response.body; 
          this.authService.msgReponseStatusDto = {title:this.authService.authResponseDto.title,
            datestamp:this.authService.authResponseDto.datestamp,timestamp:this.authService.authResponseDto.timestamp,
            status:this.authService.authResponseDto.status,message:this.authService.authResponseDto.message}; 
            if ( this.authService.msgReponseStatusDto.status === this.authService.SUCCESSFUL ) 
            { this.authService.saveLogin( this.authService.authResponseDto.token,this.authService.authRequestDto.username );}
            else { this.stateMsgBoxAuth  = true ; }
        }
        ,(error) => {
          this.stateMsgBoxAuth  = true ; 
          this.authService.msgReponseStatusDto =  
          { title : "Error",
            datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : "Error Server 500",
            } 
        }) ;
     }
    }
  closeEventstateMsgBoxAuth($event:any):void {  
    this.stateMsgBoxAuth = $event;
  }
  onClickForgotPassword() :void {this.authService.goToComponent('forgot-password'); }
  signUp():void {this.authService.goToComponent('sign-up'); }
}
