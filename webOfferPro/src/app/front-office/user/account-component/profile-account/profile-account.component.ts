import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/front-office/services/user/account.service'; 
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { AttachementDto } from 'src/app/models/user_model/AttachementDto';
import { Category } from 'src/app/models/user_model/Category';
import { ActivatedRoute } from '@angular/router';
import { AccountDto } from 'src/app/models/user_model/AcountDto';

@Component({
  selector: 'app-profile-account',
  templateUrl: './profile-account.component.html',
  styleUrls: ['./profile-account.component.css']
})
export class ProfileAccountComponent implements OnInit {
  private username! : string;

  constructor(public accountService : AccountService,
    public authService : AuthenticationService ,  private route: ActivatedRoute ) {}

  ngOnInit(): void {
    this.username = this.route.snapshot.params['username'];
    console.log( this.username );
    this.accountService. getByUsername(this.username).subscribe(
      (response) => {
        const account : AccountDto = response.body;
        this.accountService.accountDto = account;
       }
      ,(error) => {
        this.stateMsgBoxAuth = true ; 
        this.authService.msgReponseStatusDto =  
        { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
      }) ;


    //this.accountService.accountDto = this.accountService.getAccoutDto(); 
    this.accountService.coverProfile = this.accountService.getLastPhotoProfile(this.accountService.accountDto.attachementsDto,Category.COVERPICTURE);
    console.log(   this.accountService.accountDto);
    }

  public onClickToGoEditAccount():void {this.accountService.goToComponent('user/account/update-profile');}




  //Msg box
  stateMsgBoxAuth : boolean = false;
  closeEventstateMsgBoxAuth($event:any):void {this.stateMsgBoxAuth = $event;}


  //Personal Information  upload image profile
  stateMsgBoxUploadImgCoverPersonalInformation : boolean = false;
  onClickOnUploadCoverPersonalInformation():void { this.stateMsgBoxUploadImgCoverPersonalInformation = true;}
  onYesNoEventUploadCoverImgPersonalInformation($event:any):void {this.stateMsgBoxUploadImgCoverPersonalInformation = $event ;} 
  uploadCoverProfile($event:File):void {
  this.accountService.updatePhotoCover(  this.accountService.accountDto.userDto.username , $event ).subscribe(
    (response) => {
      const photo_Profile : AttachementDto = response.body;
      this.accountService.accountDto.attachementsDto.push(photo_Profile);
      this.accountService.photoProfile = this.accountService.getLastPhotoProfile(this.accountService.accountDto.attachementsDto , Category.COVERPICTURE);
      this.accountService.setAccountDto(this.accountService.accountDto);
     }
    ,(error) => {
      this.stateMsgBoxAuth = true ; 
      this.authService.msgReponseStatusDto =  
      { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
    }) ;
}
}
