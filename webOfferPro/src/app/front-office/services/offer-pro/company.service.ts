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

@Injectable({
  providedIn: 'root'
})
export class CompanyService {
  private url = `${environment.apiBaseUrl}`;
  public companyDto : CompanyDto = new CompanyDto(); 
  public ListcompanysDto:CompanyDto[]=[];
  public msgReponseStatusDto  : MsgReponseStatusDto = new MsgReponseStatusDto(); 
  logo:string = "assets/images/user/11.png"; 
  cover:string = "assets/images/page-img/profile-bg1.jpg"; 


  constructor(private http:HttpClient,
              private router: Router,
              private activeRoute: ActivatedRoute,
              private accountService: AccountService)
    {

      this.companyDto.logo.downloadURL ="assets/images/user/11.png";
      this.companyDto.cover.downloadURL ="assets/images/page-img/profile-bg1.jpg";

     
      this.accountService.accountDto = this.accountService.getAccoutDto();
      if (this.accountService.accountDto != new AccountDto){
      this.getByUsername( this.accountService.accountDto.userDto.username ).subscribe(
        (response) => {
          const companyDto : CompanyDto = response.body;
          
          this.setCompanyDto(companyDto)
         }
        ,(error) => {
          console.log("Error CompanyService -> getByName : "+  (error.message || error.error)); 
        }) ;
      }

    }




    goToComponent(component:string) : void {this.router.navigateByUrl(component);} // eq de routerLink="child1"


    getCompanyDto() : CompanyDto{
      this.accountService.accountDto = this.accountService.getAccoutDto();
      if (this.accountService.accountDto == new AccountDto){return new CompanyDto();}
      return this.accountService.accountDto.companyDto;
    }
    clearCompanyDto() : void{
      this.accountService.accountDto = this.accountService.getAccoutDto();
      if (this.accountService.accountDto == new AccountDto){return;}
      this.accountService.accountDto.companyDto = new CompanyDto();
      this.accountService.setAccountDto(this.accountService.accountDto);
    }
    setCompanyDto(companyDto:CompanyDto) :void{
    this.accountService.accountDto = this.accountService.getAccoutDto(); 
    if (this.accountService.accountDto == new AccountDto){return;}
    this.accountService.accountDto.companyDto =  companyDto;
    this.accountService.setAccountDto(this.accountService.accountDto);}

    
    create(company:CompanyDto) : Observable<HttpResponse<any>> {
          return this.http.post(`${this.url}/offer-pro-service/company/create`,company , {observe : 'response'
          ,headers: new HttpHeaders({ 'Content-Type': 'application/json' })})
         }
    getByName(name: any) : Observable<HttpResponse<any>>{
          return this.http.get(`${this.url}/offer-pro-service/company/name/${name}`,
                         {observe : 'response'})
        }
    getByUsername(username: any) : Observable<HttpResponse<any>>{
           return this.http.get(`${this.url}/offer-pro-service/company/username/${username}`,
                          {observe : 'response'})
         }
    update(id:number, company:CompanyDto) : Observable<HttpResponse<any>>{
          return this.http.put(`${this.url}/offer-pro-service/company/${id}`,
                                company,
                                {observe : 'response'})
        }
    updateLogoCompany( idCompany:number , file:File ) : Observable<HttpResponse<any>> {
          const formData = new FormData();   // Create form data
          formData.append("file", file, file.name); // Store form name as "file" with file data
          return this.http.put(`${this.url}/offer-pro-service/company/upload-logo/${idCompany}` ,formData, 
          {observe : 'response'})
        }
    updateCoverCompany( idCompany:number , file:File ) : Observable<HttpResponse<any>> {
          const formData = new FormData();   // Create form data
          formData.append("file", file, file.name); // Store form name as "file" with file data
          return this.http.put(`${this.url}/offer-pro-service/company/upload-cover/${idCompany}` ,formData, 
          {observe : 'response'})
        }


      
        public  listActivityArea = [
          {  label: 'CALL CENTERS', value:  ActivityArea.CALL_CENTERS },  
          {  label: 'TRAINING', value:  ActivityArea.TRAINING },
          {  label: 'HUMAN RESSOURCES', value:  ActivityArea.HUMAN_RESSOURCES },
          {  label: 'ENGINEERING', value:  ActivityArea.ENGINEERING },
          {  label: 'MARKETING', value:  ActivityArea.MARKETING },
          {  label: 'COMPUTER SCIENCE', value:  ActivityArea.COMPUTER_SCIENCE },
          {  label: 'TELECOMMUNICATIONS', value:  ActivityArea.TELECOMMUNICATIONS },
          {  label: 'INFORMATION TECHNOLOGY', value:  ActivityArea.INFORMATION_TECHNOLOGY },
          {  label: 'TRADE', value:  ActivityArea.TRADE },
          {  label: 'SALE', value:  ActivityArea.SALE },
          {  label: 'TRANSPORTATION', value:  ActivityArea.TRANSPORTATION },
          {  label: 'SCIENCE', value:  ActivityArea.SCIENCE },
          {  label: 'RESEARCH', value:  ActivityArea.RESEARCH },
          {  label: 'REAL ESTATE', value:  ActivityArea.REAL_ESTATE },
          {  label: 'QUALITY CONTROL', value:  ActivityArea.QUALITY_CONTROL },
          {  label: 'PURCHASING PROCUEMENT', value:  ActivityArea.PURCHASING_PROCUEMENT },
          {  label: 'RESPHARMACEUTICALSEARCH', value:  ActivityArea.RESPHARMACEUTICALSEARCH },
          {  label: 'CLIENT SERVICES', value:  ActivityArea.CLIENT_SERVICES },
          {  label: 'Media JOURNALISM', value:  ActivityArea.Media_JOURNALISM },
          {  label: 'MANAGEMENT', value:  ActivityArea.MANAGEMENT },
          {  label: 'LEGAL', value:  ActivityArea.LEGAL },
          {  label: 'INSURANCE', value:  ActivityArea.INSURANCE },
          {  label: 'INSTALLATION MAINTENANCE REPAIR', value:  ActivityArea.INSTALLATION_MAINTENANCE_REPAIR },
          {  label: 'DESIGN', value:  ActivityArea.DESIGN }
        ];
}
