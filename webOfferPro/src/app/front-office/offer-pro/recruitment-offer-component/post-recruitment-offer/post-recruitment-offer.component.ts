import { Component, Input, OnInit } from '@angular/core';
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
import { CompanyDto } from 'src/app/models/offer_pro_model/CompanyDto';
import { RecruitmentService } from 'src/app/front-office/services/offer-pro/recruitment.service';
import { RecruitmentOfferDto } from 'src/app/models/offer_pro_model/RecruitmentOfferDto';

@Component({
  selector: 'app-post-recruitment-offer',
  templateUrl: './post-recruitment-offer.component.html',
  styleUrls: ['./post-recruitment-offer.component.css']
})
export class PostRecruitmentOfferComponent  implements OnInit {
  @Input() recruitmentOfferDto: RecruitmentOfferDto = new RecruitmentOfferDto();
  textDescription: string = "";
  constructor(public companyService : CompanyService,
    public accountService : AccountService,
    public recruitmentService : RecruitmentService  ) {   }

  ngOnInit(): void {
    this.recruitmentOfferDto.startDateTime = new Date(this.recruitmentOfferDto.startDateTime);
    this.recruitmentOfferDto.endDateTime = new Date(this.recruitmentOfferDto.endDateTime);
    //this.recruitmentService.recruitmentOfferDto = this.recruitmentOfferDto;
    this.textDescription = (this.recruitmentOfferDto.description != null && this.recruitmentOfferDto.description.length >=200 ? 
    this.recruitmentOfferDto.description.substring(0, 200) +"...":this.recruitmentOfferDto.description); 
 // Get the time in 12-hour format (e.g., "1:30 PM")
/*var timeIn12HourFormat = this.recruitmentOfferDto.startDateTime.toLocaleTimeString('en-US', {
  hour: 'numeric',
  minute: 'numeric',
  hour12: true,
});*/ 
  }
   

  
  goToDetailed():void{
    this.recruitmentService.recruitmentOfferDto = this.recruitmentOfferDto;
    this.recruitmentService.goToComponent("recruitment-offer/detailed?id="+
    this.recruitmentService.recruitmentOfferDto.id+"&title="+
    this.recruitmentService.recruitmentOfferDto.title.replace(/ /g, '%20'));
  }



  stateBoxAddApplyOffer : boolean = false;
  onClickOpenCloseAddApplyOfferEvent($event:any):void {this.stateBoxAddApplyOffer = $event;}
  onClickApplyOnOffer():void{this.stateBoxAddApplyOffer = true;}
}
