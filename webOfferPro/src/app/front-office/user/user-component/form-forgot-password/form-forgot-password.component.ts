import { Component,OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service'; 
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';  
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-form-forgot-password',
  templateUrl: './form-forgot-password.component.html',
  styleUrls: ['./form-forgot-password.component.css']
})
export class FormForgotPasswordComponent implements OnInit {

  stateMsgBoxAuth : boolean = false;
  constructor( public authService : AuthenticationService  ) { }
  ngOnInit(): void {}
  onClickForgotPassword(form: NgForm):void {      /*this.stateMsgBoxAuth = true;*/ }
  closeEventstateMsgBoxAuth($event:any):void {  
    this.stateMsgBoxAuth = $event;
  } 
  signIn():void {this.authService.goToComponent('sign-in'); }
}
