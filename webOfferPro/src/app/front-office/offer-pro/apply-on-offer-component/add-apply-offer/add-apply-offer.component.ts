import {  Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { RecruitmentService } from 'src/app/front-office/services/offer-pro/recruitment.service';
import { AccountService } from 'src/app/front-office/services/user/account.service';
import { ApplyOnOfferDto } from 'src/app/models/offer_pro_model/ApplyOnOfferDto';
import { RecruitmentOfferDto } from 'src/app/models/offer_pro_model/RecruitmentOfferDto';

@Component({
  selector: 'app-add-apply-offer',
  templateUrl: './add-apply-offer.component.html',
  styleUrls: ['./add-apply-offer.component.css']
})
export class AddApplyOfferComponent implements OnInit  {
  constructor(private recuirementService : RecruitmentService,
    private accountService : AccountService) { }

  ngOnInit(): void {console.log(this.recuirementOfferDto);} 

  @Input() state : boolean = false;
  @Input() recuirementOfferDto : RecruitmentOfferDto = new RecruitmentOfferDto;
  @Output() onClickOpenCloseEvent=new EventEmitter<boolean>();  

  onClickOpenClose( state:boolean ):void {  
    this.onClickOpenCloseEvent.emit(state);
  }

  onClickOnSubmitApplyOffer(ApplyOffer:any):void{
    console.log(ApplyOffer.invalid);
    if ( !ApplyOffer.invalid ){
      this.state = false;
      this.onClickOpenCloseEvent.emit(this.state);
      let applyOnOfferDto : ApplyOnOfferDto = new ApplyOnOfferDto(); 
      applyOnOfferDto.username = this.accountService.getAccoutDto().userDto.username;
      this.recuirementService.addApplyOnOfferByRecruitmentOfferAndAccount(this.recuirementOfferDto.id,applyOnOfferDto)
    }
  }
}
