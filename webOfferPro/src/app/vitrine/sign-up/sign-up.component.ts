import { Component,OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {


  constructor( public authService : AuthenticationService  ) {
     this.authService.msgReponseStatusDto =  
     { title : "Error",
      datestamp: new Date() , timestamp: new Date()
      , status : ReponseStatus.ERROR ,
      message : "Your mail is correct so you see your email for complete next step",
       } }

  ngOnInit(): void {}


  signUp(form: NgForm):void {   console.log(form.invalid);
    if(!form.invalid){
    this.authService.register(this.authService.authRequestDto).subscribe(
      (response) => {     
        this.authService.msgReponseStatusDto = response.body;
        if ( this.authService.msgReponseStatusDto.status === this.authService.SUCCESSFUL ) 
        {this.authService.goToComponent("success-sign-up/"+this.authService.authRequestDto.email);}
        else { this.stateMsgBoxAuth  = true ; }
      }
      ,(error) => {
        this.stateMsgBoxAuth  = true ; 
        this.authService.msgReponseStatusDto =  
        { title : "Error",
          datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : "Error Server 500",
          } 
      }) ;
   }}


  stateMsgBoxAuth : boolean = false;
  closeEventstateMsgBoxAuth($event:any):void {   this.stateMsgBoxAuth = $event; }















  stateMsgBoxTearmsCondition : boolean = false;
  closeEventstateMsgBoxTearmsCondition($event:any):void {   this.stateMsgBoxTearmsCondition = $event; }
  onClickTearmsCondition():void { 
    this.authService.msgReponseStatusDto =  
    { datestamp: new Date() , timestamp: new Date()
     , status : ReponseStatus.SUCCESSFUL,  
       message : "TERMS AND CONDITIONS\n 1. ACCEPTANCE OF TERMS\n By accessing or using this website [YourWebsite.com] (the 'Website') or any of its services, you agree to comply with and be bound by these Terms and Conditions.",
       title : "Error"}; 
    this.stateMsgBoxTearmsCondition = true; }

  signIn():void {this.authService.goToComponent('sign-in');  }
}
