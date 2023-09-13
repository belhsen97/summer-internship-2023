import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AccountDto } from 'src/app/models/user_model/AcountDto';
import { AttachementDto } from 'src/app/models/user_model/AttachementDto';
import { Gender } from 'src/app/models/user_model/Gender';
import { Category } from 'src/app/models/user_model/Category';
import { StateRegion } from 'src/app/models/user_model/StateRegion';
import { environment } from 'src/environments/environment';
import { CompanyDto } from 'src/app/models/offer_pro_model/CompanyDto';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { RecruitmentOfferDto } from 'src/app/models/offer_pro_model/RecruitmentOfferDto';
import { ActivityArea } from 'src/app/models/offer_pro_model/ActivityArea';
import { AccountService } from '../user/account.service';
import { ContractType } from 'src/app/models/offer_pro_model/ContractType';
import { StudyLevel } from 'src/app/models/offer_pro_model/StudyLevel';
import { TypeOffer } from 'src/app/models/offer_pro_model/TypeOffer';
import { ApplyOnOfferDto } from 'src/app/models/offer_pro_model/ApplyOnOfferDto';

@Injectable({
  providedIn: 'root'
})
export class RecruitmentService {
  private url = `${environment.apiBaseUrl}`;
  public recruitmentOfferDto : RecruitmentOfferDto = new RecruitmentOfferDto(); 
  public ListrecruitmentOfferDto:RecruitmentOfferDto[]=[];
  public msgReponseStatusDto  : MsgReponseStatusDto = new MsgReponseStatusDto(); 
  constructor(private http:HttpClient,
              private router: Router,
              private activeRoute: ActivatedRoute,
              private accountService: AccountService) {}

   goToComponent(component:string) : void {this.router.navigateByUrl(component);} // eq de routerLink="child1"

   create(idCompany:number,recruitmentOfferDto:RecruitmentOfferDto) : Observable<HttpResponse<any>> {
    return this.http.put(`${this.url}/offer-pro-service/company/add-offer/`+idCompany,recruitmentOfferDto,
    {observe : 'response',
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })});
   }
   getAll() : Observable<HttpResponse<any>>{
    return this.http.get(`${this.url}/offer-pro-service/recruitment-offer`,{observe : 'response'});}

   getByIdAndTitle(id:number,title:string) : Observable<HttpResponse<any>>{
    return this.http.get(`${this.url}/offer-pro-service/recruitment-offer/${id}/title/${title}`,{observe : 'response'});}

 
    addApplyOnOfferByRecruitmentOfferAndAccount(idRecruitmentOffer:number,applyOnOfferDto:ApplyOnOfferDto) : Observable<HttpResponse<any>>{
      return this.http.put(`${this.url}/offer-pro-service/apply-on-offer/add-by-recruitment-offer-and-by-account/${idRecruitmentOffer}`
      ,applyOnOfferDto,
      {observe : 'response',
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })});
    }

 
    public  listContractType = [{label:'CIVP',value:ContractType.CIVP},{label:'CDD',value:ContractType.CDD},{label:'CDI',value:ContractType.CDI},{label:'KARAMA',value:ContractType.KARAMA},{label:'SEASONAL',value:ContractType.SEASONAL},{label:'ALTERNATIION',value:ContractType.ALTERNATIION},{label:'FREELANCER',value:ContractType.FREELANCER},   ];
    public  listTypeOffer = [{label:'INTERNSHIP',value:TypeOffer.INTERNSHIP },{label:'JOB',value:TypeOffer.JOB}];
    public  listStudyLevel = [{label:'Licence',value:StudyLevel.LICENCE },{label:'Master',value:StudyLevel.MASTER},{label:'Engineering',value:StudyLevel.ENGINEERING}];
 

    public parseFormatDate (date:Date):string{
      return this.recruitmentOfferDto.startDateTime.getFullYear() + "/" + (this.recruitmentOfferDto.startDateTime.getMonth() + 1) +
      "/" + this.recruitmentOfferDto.startDateTime.getDate() + " " + this.recruitmentOfferDto.startDateTime.getHours() +
      ":" + this.recruitmentOfferDto.startDateTime.getMinutes();
    }
    public toDate (str:string):Date{
      return new Date (str );
    }
    public formatDateAgo(date: Date): string {
      const now = new Date();
      const timeDifference = now.getTime() - date.getTime();
    
      // Calculate time units
      const minutes = Math.floor(timeDifference / 60000); // 1 minute = 60000 milliseconds
      const hours = Math.floor(minutes / 60);
      const days = Math.floor(hours / 24);
    
      // Construct the formatted string
      let formattedDate = "";
    
      if (days > 0) {
        formattedDate += `${days} day${days > 1 ? 's' : ''} `;
      }
      
      const remainingHours = hours % 24;
      if (remainingHours > 0) {
        formattedDate += `${remainingHours} hour${remainingHours > 1 ? 's' : ''} `;
      }
    
      const remainingMinutes = minutes % 60;
      if (remainingMinutes > 0) {
        formattedDate += `${remainingMinutes} minute${remainingMinutes > 1 ? 's' : ''} `;
      }
    
      formattedDate += "ago";
    
      return formattedDate;
    }
    
}
