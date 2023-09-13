import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/front-office/services/user/account.service';
import { NgForm } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { Gender } from 'src/app/models/user_model/Gender';
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { AttachementDto } from 'src/app/models/offer_pro_model/AttachementDto';
import { Category } from 'src/app/models/user_model/Category';
import { CompanyService } from 'src/app/front-office/services/offer-pro/company.service';
import { ActivatedRoute } from '@angular/router';
import { RecruitmentService } from 'src/app/front-office/services/offer-pro/recruitment.service';
import { RecruitmentOfferDto } from 'src/app/models/offer_pro_model/RecruitmentOfferDto';


@Component({
  selector: 'app-details-company',
  templateUrl: './details-company.component.html',
  styleUrls: ['./details-company.component.css']
})
export class DetailsCompanyComponent  implements OnInit {
  //private companyName! : string;
  private companyName! : string | null;

  constructor(public companyService : CompanyService ,
             private route: ActivatedRoute ,
             public authService : AuthenticationService,
             public accountService : AccountService,
             public recruitmentService : RecruitmentService) {
  }
 ngOnInit(): void {
  this.route.queryParamMap.subscribe(params => {this.companyName = params.get('Name');});
  //this.companyName = this.route.snapshot.params['Name'];
  this. getCompanyByName ((this.companyName == null? "":this.companyName));
  console.log( this.companyName );


  this.recruitmentService.getAll().subscribe(
    (response) => {
     this.recruitmentService.ListrecruitmentOfferDto = response.body; 
     }
    ,(error) => {
     console.log('page-error : ' +error.message);
    }) ;

 }


 getCompanyByName (name:String): void {
  this.companyService.getByName( name ).subscribe(
    (response) => {
      this.companyService.companyDto = response.body;
     }
    ,(error) => {
      this.companyService.goToComponent('page-error');
    }) ;
 }



  //Msg box
  stateMsgBoxAuth : boolean = false;
  closeEventstateMsgBoxAuth($event:any):void {this.stateMsgBoxAuth = $event;}

 

stateBoxUploadImgCoverCompany : boolean = false;
onClickOnUploadCoverCompany (): void {this.stateBoxUploadImgCoverCompany = true;}
onYesNoEventUploadCoverImgCompany ($event:boolean): void {this.stateBoxUploadImgCoverCompany = $event ;}
uploadCoverCompany ($event:File): void {
  this.companyService.updateCoverCompany(this.companyService.companyDto.id, $event ).subscribe(
    (response) => {
      const cover : AttachementDto = response.body;
      this.companyService.companyDto.cover = cover;
      this.companyService.setCompanyDto(this.companyService.companyDto); 
     }
    ,(error) => {
      this.stateMsgBoxAuth = true ; 
      this.companyService.msgReponseStatusDto =  
      { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
    }) ;
}






stateBoxUploadImgLogoCompany : boolean = false;
onClickOnUploadLogoCompany (): void {this.stateBoxUploadImgLogoCompany = true;}
onYesNoEventUploadLogoImgCompany ($event:boolean): void {this.stateBoxUploadImgLogoCompany = $event ;}
uploadLogoCompany ($event:File): void {
  this.companyService.updateLogoCompany(this.companyService.companyDto.id, $event ).subscribe(
    (response) => {
      const logo : AttachementDto = response.body;
      this.companyService.companyDto.logo = logo;
      this.companyService.setCompanyDto(this.companyService.companyDto); 
     }
    ,(error) => {
      this.stateMsgBoxAuth = true ; 
      this.companyService.msgReponseStatusDto =  
      { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
    }) ;
}


 onClickToGoEditCompany (): void {
    this.recruitmentService.goToComponent("company/edit");
 }


 onAddRecruitmentOffer($recruitment: RecruitmentOfferDto): void { this.recruitmentService.ListrecruitmentOfferDto.push($recruitment);}

 
}
