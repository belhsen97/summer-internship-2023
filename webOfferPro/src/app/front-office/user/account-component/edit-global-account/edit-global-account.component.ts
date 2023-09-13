import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/front-office/services/user/account.service';
import { NgForm } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { Gender } from 'src/app/models/user_model/Gender';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { AttachementDto } from 'src/app/models/user_model/AttachementDto';
import { Category } from 'src/app/models/user_model/Category';

@Component({
  selector: 'app-edit-global-account',
  templateUrl: './edit-global-account.component.html',
  styleUrls: ['./edit-global-account.component.css']
})
export class EditGlobalAccountComponent implements OnInit {
  constructor(public accountService : AccountService,public authService : AuthenticationService ) {}
   ngOnInit(): void {
    this.accountService.accountDto = this.accountService.getAccoutDto(); 
    this.accountService.photoProfile = this.accountService.getLastPhotoProfile(this.accountService.accountDto.attachementsDto,Category.PHOTOPROFILE);
    console.log(  this.accountService.photoProfile );
    }

  //Msg box
   stateMsgBoxAuth : boolean = false;
   closeEventstateMsgBoxAuth($event:any):void {this.stateMsgBoxAuth = $event;}


//Personal Information  upload image profile
  stateMsgBoxUploadImgPersonalInformation : boolean = false;
  onClickOnUploadPersonalInformation():void { this.stateMsgBoxUploadImgPersonalInformation = true;}
  onYesNoEventUploadImgPersonalInformation($event:any):void {this.stateMsgBoxUploadImgPersonalInformation = $event ;} 
uploadImageProfile($event:File):void {
  this.accountService.updatePhotoProfile(  this.accountService.accountDto.userDto.username , $event ).subscribe(
    (response) => {
      const photo_Profile : AttachementDto = response.body;
      this.accountService.accountDto.attachementsDto.push(photo_Profile);
      this.accountService.photoProfile = this.accountService.getLastPhotoProfile(this.accountService.accountDto.attachementsDto,Category.PHOTOPROFILE);
      this.accountService.setAccountDto(this.accountService.accountDto);
     }
    ,(error) => {
      this.stateMsgBoxAuth = true ; 
      this.authService.msgReponseStatusDto =  
      { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
    }) ;
}

//Personal Information
  stateMsgBoxConfimPersonalInformation : boolean = false;
  onClickOnSubmitPersonalInformation(form: NgForm):void { this.stateMsgBoxConfimPersonalInformation =  !form.invalid;}
  onClickEventMsgConfimPersonalInformation($event:any):void {
   this.stateMsgBoxConfimPersonalInformation = $event ;
   if($event){this.stateMsgBoxConfimPersonalInformation = false;
       this.accountService.update( this.accountService.accountDto .id,this.accountService.accountDto) .subscribe(
        (response) => {     
          this.accountService.accountDto = response.body;
          this.accountService.setAccountDto(this.accountService.accountDto);
        }
        ,(error) => {
            this.stateMsgBoxAuth = true ; 
            this.authService.msgReponseStatusDto =  
            { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
        }) ;
  }}
  onCheckRadioGenderOption(gender:Gender):void {this.accountService.accountDto.gender = gender ;}










//Change Password
isNotSamePassword : boolean = false;
public currentPassword! :string ;
public newPassword! : string;
public valodatePassword! : string;
stateMsgBoxConfimChangePassword : boolean = false;
onClickSubmitUpdatePassword(form: NgForm):void {this.stateMsgBoxConfimChangePassword = !form.invalid; }
onClickEventMsgConfimChangePassword ($event:any):void {
  this.stateMsgBoxConfimChangePassword = $event ;
  if($event){this.stateMsgBoxConfimChangePassword = false;
    this.authService.updatePassword( this.accountService.accountDto.userDto.username,this.currentPassword,this.newPassword) .subscribe(
      (response) => {
        this.authService.msgReponseStatusDto = response.body;
        this.stateMsgBoxAuth=true;
      }
      ,(error) => {
        this.authService.msgReponseStatusDto =  
        { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
        this.stateMsgBoxAuth=true;
      }) ; }
}
onClickForgotPassword() :void {this.accountService.goToComponent('forgot-password'); }
validatorPassword():void{if (this.newPassword != this.valodatePassword){this.isNotSamePassword = true; }
else {this.isNotSamePassword = false; } }











//Manage Contact
stateMsgBoxConfimManageContact : boolean = false; 
onClickSubmitManageContact(form: NgForm):void {   this.stateMsgBoxConfimManageContact =!form.invalid; }
onClickEventMsgConfimManageContact($event:any):void {   
  this.stateMsgBoxConfimManageContact = $event ;
  if($event){this.stateMsgBoxConfimManageContact = false;
  this.accountService.update( this.accountService.accountDto .id,this.accountService.accountDto) .subscribe(
    (response) => {     
      this.accountService.accountDto = response.body;
      this.accountService.setAccountDto(this.accountService.accountDto);
    }
    ,(error) => {
      this.authService.msgReponseStatusDto =  
      { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
      this.stateMsgBoxAuth=true;
    });
  }
} 


}
