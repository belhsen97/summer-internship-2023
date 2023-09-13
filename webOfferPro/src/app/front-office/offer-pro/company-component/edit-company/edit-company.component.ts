import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/front-office/services/user/account.service';
import { NgForm } from '@angular/forms';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { CompanyService } from 'src/app/front-office/services/offer-pro/company.service';
import { CompanyDto } from 'src/app/models/offer_pro_model/CompanyDto';

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.css']
})
export class EditCompanyComponent implements OnInit {

  constructor(public companyService : CompanyService,
              public accountService : AccountService) {
                console.log(accountService.accountDto);
  }
 ngOnInit(): void {    this.companyService.companyDto =  this.companyService.getCompanyDto(); }



  //Msg box
  stateMsgBoxAuth : boolean = false;
  closeEventstateMsgBoxAuth($event:any):void {this.stateMsgBoxAuth = $event;}
 
//Basic Information
stateMsgBoxConfimEditBasicInfo : boolean = false;
onClickOnSubmitEditBasicInfo(form: NgForm):void { this.stateMsgBoxConfimEditBasicInfo =  !form.invalid;}
onClickEventMsgConfimEditBasicInfo($event:any):void {
  this.stateMsgBoxConfimEditBasicInfo = $event ;
  if($event){
    this.stateMsgBoxConfimEditBasicInfo = false;
    this.companyService.update(  this.companyService.companyDto.id, this.companyService.companyDto  ).subscribe(
      (response) => {
        const companyDto : CompanyDto = response.body;
        this.companyService.companyDto = companyDto;
        this.companyService.setCompanyDto(companyDto);
       }
      ,(error) => {
        this.stateMsgBoxAuth = true ; 
        this.companyService.msgReponseStatusDto =  
        { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
      }) ;
  }
}



//Contact Information
stateMsgBoxConfimEditContactInfo : boolean = false;
onClickOnSubmitEditContactInfo(form: NgForm):void { this.stateMsgBoxConfimEditContactInfo =  !form.invalid;}
onClickEventMsgConfimEditContactInfo($event:any):void {
  this.stateMsgBoxConfimEditContactInfo = $event ;
  if($event){
    this.stateMsgBoxConfimEditContactInfo = false;
    this.companyService.update(  this.companyService.companyDto.id, this.companyService.companyDto  ).subscribe(
      (response) => {
        const companyDto : CompanyDto = response.body;
        this.companyService.companyDto = companyDto;
        this.companyService.setCompanyDto(companyDto);
       }
      ,(error) => {
        this.stateMsgBoxAuth = true ; 
        this.companyService.msgReponseStatusDto =  
        { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
      }) ;
  }
}


//Contact Information
stateMsgBoxConfimEditWebsitesSocialLinksInfo : boolean = false;
onClickOnSubmitEditWebsitesSocialLinksInfoForm(form: NgForm):void { this.stateMsgBoxConfimEditWebsitesSocialLinksInfo =  !form.invalid;}
onClickEventMsgConfimEditWebsitesSocialLinksInfo($event:any):void {
  this.stateMsgBoxConfimEditWebsitesSocialLinksInfo = $event ;
  if($event){
    this.stateMsgBoxConfimEditWebsitesSocialLinksInfo = false;
    this.companyService.update(  this.companyService.companyDto.id, this.companyService.companyDto  ).subscribe(
      (response) => {
        const companyDto : CompanyDto = response.body;
        this.companyService.companyDto = companyDto;
        this.companyService.setCompanyDto(companyDto);
       }
      ,(error) => {
        this.stateMsgBoxAuth = true ; 
        this.companyService.msgReponseStatusDto =  
        { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
      }) ;
  }
}



}
