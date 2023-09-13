import { Component,OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { ActivatedRoute } from '@angular/router';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-success-sign-up',
  templateUrl: './success-sign-up.component.html',
  styleUrls: ['./success-sign-up.component.css']
})
export class SuccessSignUpComponent {
  public email : string = "abc@exemple.com";
  constructor( private route: ActivatedRoute ) {
    this.email = this.route.snapshot.params['email'];
  }
}
