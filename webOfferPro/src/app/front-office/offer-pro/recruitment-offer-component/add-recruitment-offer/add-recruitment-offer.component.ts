import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AccountService } from 'src/app/front-office/services/user/account.service';
import { NgForm } from '@angular/forms'; 
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus'; 
import { CompanyService } from 'src/app/front-office/services/offer-pro/company.service'; 
import { RecruitmentService } from 'src/app/front-office/services/offer-pro/recruitment.service';
import { RecruitmentOfferDto } from 'src/app/models/offer_pro_model/RecruitmentOfferDto';
import { FormDto } from 'src/app/models/offer_pro_model/FormDto';
import { FormService } from 'src/app/front-office/services/offer-pro/form.service';

@Component({
  selector: 'app-add-recruitment-offer',
  templateUrl: './add-recruitment-offer.component.html',
  styleUrls: ['./add-recruitment-offer.component.css']
})
export class AddRecruitmentOfferComponent implements OnInit {
  @Output() onAddRecruitmentOffer =new EventEmitter<RecruitmentOfferDto>();
  formsDto : FormDto[] = new Array<FormDto>();  ;
  
  constructor(public companyService : CompanyService,
              public accountService : AccountService,
              public recruitmentService : RecruitmentService,
              public serviceForm:FormService ) {  }

ngOnInit(): void { }


stateX : boolean = false;
clickEventStateYes():void { this.stateX= true; console.log(this.stateX);} 
clickEventStateNo():void { this.stateX= false; console.log(this.stateX);} 


  //Msg box
  stateMsgBoxAuth : boolean = false;
  closeEventstateMsgBoxAuth($event:any):void {this.stateMsgBoxAuth = $event;}







  //add-form
  stateOpenCloseAddFormBox : boolean = false;
  onEventStateOpenCloseAddFormBox ($event:any):void {this.stateOpenCloseAddFormBox = $event;}
  onClickOpenAddForms():void{this.stateOpenCloseAddFormBox = true;}
  onEventFormDtoAddFormBox (formDto:FormDto):void {
   // this.spanBigChar = document.getElementById('add-recruitment-offer-forms-div');
   
   console.log(formDto);
   this.incrementForm +=1;
   formDto.id = this.incrementForm;
  
    this.formsDto.push(formDto); 
  //  this.spanBigChar.innerHTML =  this.serviceForm.uiDesignAlerteForm(formdto);
  }
  incrementForm:number=0;
 // spanBigChar : any = null;
  onClickDeleteForm(id:number):void{
    this.formsDto =  this.serviceForm.deleteFormById(id,
      this.formsDto);
  }






 
//Basic Information 
onClickOnSubmitAddRecruitmentOffer (form: NgForm):void {
  if (!form.invalid){ 
    this.recruitmentService.recruitmentOfferDto.forms = this.formsDto.map(({ id, ...rest }) => rest); 
    this.recruitmentService.recruitmentOfferDto.endDateTime =  new Date( this.recruitmentService.recruitmentOfferDto.endDateTime);
    console.log(this.recruitmentService.recruitmentOfferDto)
    this.recruitmentService.create(  this.companyService.getCompanyDto().id, this.recruitmentService.recruitmentOfferDto).subscribe(
      (response) => {
        const recruitmentOfferDto : RecruitmentOfferDto = response.body;
        this.onAddRecruitmentOffer.emit(recruitmentOfferDto); 
       this.stateMsgBoxAuth = true ; 
       this.recruitmentService.msgReponseStatusDto =  
       { title : "Successful", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.SUCCESSFUL , message : "success to add new offer"};
       }
      ,(error) => {
        this.stateMsgBoxAuth = true ; 
        this.recruitmentService.msgReponseStatusDto =  
        { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};
      }) ;

  }
}
  
}
