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
  selector: 'app-search-recruitment-offer',
  templateUrl: './search-recruitment-offer.component.html',
  styleUrls: ['./search-recruitment-offer.component.css']
})
export class SearchRecruitmentOfferComponent implements OnInit {
  //private companyName! : string;
  private keySearch! : string | null;
  public recruitmentOffersDtoFilter : RecruitmentOfferDto [] =  new Array<RecruitmentOfferDto>();
  constructor(
             private route: ActivatedRoute ,
             public recruitmentService : RecruitmentService,
             public companyService: CompanyService,
             public accountService: AccountService) {
  }
 ngOnInit(): void {
  this.route.queryParamMap.subscribe(params => {this.keySearch = params.get('key');});
  this.recruitmentService.getAll().subscribe(
    (response) => {
      const recruitmentOffersDto : RecruitmentOfferDto [] =  response.body; 
      this.recruitmentService.ListrecruitmentOfferDto = recruitmentOffersDto.map((offer) => ({
        ...offer,
        startDateTime: new Date(offer.startDateTime),  endDateTime: new Date(offer.endDateTime),
      }));
      this.recruitmentService.ListrecruitmentOfferDto.sort((a, b) => {
        return b.startDateTime.getTime() - a.startDateTime.getTime();  // inverse a.startDateTime.getTime() - b.startDateTime.getTime(); 
      });
      //this.recruitmentService.ListrecruitmentOfferDto = recruitmentOffersDto;
      //this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto;
      if (this.keySearch === 'All' ){this.onClickAllOffer();}
      else {
        this.onSearched((this.keySearch===null ? '' : this.keySearch));
      }
     }
    ,(error) => {
     console.log('page-error : ' +error.message);
    }) ;;
 }


 goToDetailed(id:number,title:string):void{
  title = title.replace(/ /g, '%20');
  this.recruitmentService.goToComponent("recruitment-offer/detailed?id="+
  id+"&title="+title);
}

onSearched(search:string):void{
  this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto.filter((offer) => {
    return (
      offer.typeOffer.includes(search)||
      offer.title.includes(search)||
      offer.contractType.includes(search)||
      offer.statusOffer.includes(search)||
      offer.studyLevel.includes(search)||
      offer.campanyDto.name.includes(search) ||
      offer.campanyDto.state.includes(search) ||
      offer.campanyDto.city.includes(search) 
    );
  });
}
onClickAllOffer():void{
  this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto
}

onClickTypeOffer(value:string):void{
  this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto.filter(
    (offer) => offer.typeOffer === value);
}
onClickContractType(value:string):void{
  this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto.filter(
    (offer) => offer.contractType === value);
}
onClickStudyLevel(value:string):void{
  this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto.filter(
    (offer) => offer.studyLevel === value);
}
onClickActivityArea(value:string):void{
  this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto.filter(
    (offer) => offer.campanyDto.activity === value);
}
onClickStateRegion(value:string):void{
  this.recruitmentOffersDtoFilter = this.recruitmentService.ListrecruitmentOfferDto.filter(
    (offer) => offer.campanyDto.state === value);
}
              






}
