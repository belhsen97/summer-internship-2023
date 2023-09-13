import {  Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormDto } from 'src/app/models/offer_pro_model/FormDto';
import { AccountService } from 'src/app/front-office/services/user/account.service';
import { NgForm } from '@angular/forms'; 
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus'; 
import { CompanyService } from 'src/app/front-office/services/offer-pro/company.service'; 
import { RecruitmentService } from 'src/app/front-office/services/offer-pro/recruitment.service';
import { RecruitmentOfferDto } from 'src/app/models/offer_pro_model/RecruitmentOfferDto';
import { FormService } from 'src/app/front-office/services/offer-pro/form.service';


@Component({
  selector: 'app-add-form',
  templateUrl: './add-form.component.html',
  styleUrls: ['./add-form.component.css']
})
export class AddFormComponent implements OnInit  {
  constructor(public serviceForm:FormService) { }
  ngOnInit(): void {
    this.formDto.required = false;
  } 

 

  formDto: FormDto = new FormDto();
  @Input() state : boolean = false;
  @Output() onOpenCloseEvent=new EventEmitter<boolean>();
  @Output() onFormDto=new EventEmitter<FormDto>();  

  onClickNo():void { 
    this.state = false; 
    this.onOpenCloseEvent.emit(this.state);
  }
  onClickYes():void {  
    this.state = true;  
    this.onOpenCloseEvent.emit(this.state);
  }





  onClickOnSubmitAddFormOffer (form: NgForm):void {
    if (!form.invalid){
       this.onFormDto.emit(this.formDto);
       this.state = false; 
       this.onOpenCloseEvent.emit(this.state);
      }
    }
}
