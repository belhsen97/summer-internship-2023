import { Component,OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';  
import { NgForm } from '@angular/forms';
import { AccountService } from 'src/app/front-office/services/user/account.service';
import { Category } from 'src/app/models/user_model/Category';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent  implements OnInit {

  username:string = '';



  constructor( public authService : AuthenticationService,public accountService : AccountService  ) { 
  }
  ngOnInit(): void {


    this.accountService.accountDto = this.accountService.getAccoutDto();
    this.accountService.photoProfile = this.accountService.getLastPhotoProfile(this.accountService.accountDto.attachementsDto,Category.PHOTOPROFILE);
    this.username =  this.accountService.getAccoutDto().userDto.username;
  }

  onClickSignOut() :void {
    this.authService.logout().subscribe(
      (response) => {   
        this.authService.clearAll();  
        this.authService.goToComponent('sign-in');}
      ,(error) => {}) ;
  }

// (keyup)="onChange($event)"   (change)="onChange($event)"
  onChange($event:any):void{
   console.log($event.target.value );
  }
}
