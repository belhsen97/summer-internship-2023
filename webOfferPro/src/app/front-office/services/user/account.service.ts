import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AccountDto } from 'src/app/models/user_model/AcountDto';
import { AttachementDto } from 'src/app/models/user_model/AttachementDto';
import { Gender } from 'src/app/models/user_model/Gender';
import { Category } from 'src/app/models/user_model/Category';
import { StateRegion } from 'src/app/models/user_model/StateRegion';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private url = `${environment.apiBaseUrl}`
  public accountDto : AccountDto = new AccountDto(); 
  category_photoprofile:Category = Category.PHOTOPROFILE;
  category_coverprofile:Category = Category.COVERPICTURE;
  ListAccounts:AccountDto[]=[];
  
  constructor(private http:HttpClient,
              private router: Router,
              private activeRoute: ActivatedRoute)
              { }
  goToComponent(component:string) : void {this.router.navigateByUrl(component);} // eq de routerLink="child1"
  getAll() : Observable<HttpResponse<any>>{
    return this.http.get(`${this.url}/user-service/account`,
                         {observe : 'response'})
  }
  getById(id: any) : Observable<HttpResponse<any>>{
    return this.http.get(`${this.url}/user-service/account/${id}`,
                          {observe : 'response'})
  }
  getByUsername(usename: string) : Observable<HttpResponse<any>>{
    return this.http.get(`${this.url}/user-service/account/select-by-username/${usename}`,
                         {observe : 'response'})
  }
  update(id:number ,  account:AccountDto) : Observable<HttpResponse<any>>{
    return this.http.put(`${this.url}/user-service/account/${id}`,
                           account,
                          {observe : 'response'})
  }
  delete(id:any) : Observable<HttpResponse<any>>{
    return this.http.delete(`${this.url}/user-service/account/${id}`,
                            {observe : 'response'})
  }
  updatePhotoProfile( username:string , file:File ) : Observable<HttpResponse<any>> {
    const formData = new FormData();   // Create form data
    formData.append("file", file, file.name); // Store form name as "file" with file data
    return this.http.put(`${this.url}/user-service/attachment/photo-profile-to-account/${username}` ,formData, 
    {observe : 'response'})
  }
  updatePhotoCover( username:string , file:File ) : Observable<HttpResponse<any>> {
    const formData = new FormData();   // Create form data
    formData.append("file", file, file.name); // Store form name as "file" with file data
    return this.http.put(`${this.url}/user-service/attachment/cover-profile-to-account/${username}` ,formData, 
    {observe : 'response'})
  }
 


getAccoutDto() : AccountDto{
  const accountDtoString =   localStorage.getItem('AccountDto'); 
  const account = (  accountDtoString == null ?  new AccountDto :  JSON.parse(accountDtoString)  ) ;
  return account;}
clearAccoutDto() : void{  localStorage.removeItem( 'AccountDto');  }
setAccountDto(accountDto:AccountDto) :void{  localStorage.removeItem('AccountDto');
localStorage.setItem('AccountDto',JSON.stringify(accountDto));}


photoProfile:string = "assets/images/user/11.png"; 
coverProfile:string = "assets/images/page-img/profile-bg1.jpg"; 
getLastPhotoProfile(attachmentsDtoList : AttachementDto[] , category : Category ):string {
  const photoProfileAttachments = attachmentsDtoList.filter(attachment => attachment.category === category );
  if (photoProfileAttachments.length > 0) {
    //const latestPhotoProfile = photoProfileAttachments.sort((a, b) => b.addAt.getTime() - a.addAt.getTime())[0]; 
    const latestPhotoProfile = photoProfileAttachments.reduce(
      (prevAttachment, currentAttachment) =>  prevAttachment.addAt > currentAttachment.addAt ? prevAttachment : currentAttachment);
    return latestPhotoProfile.downloadURL;
  }
return  ( category  === Category.PHOTOPROFILE ? "assets/images/user/11.png" : "assets/images/page-img/profile-bg1.jpg");
}









public FEMALE : Gender = Gender.female;
public MALE : Gender = Gender.male;

 public  listStateRegion = [{ label: 'Ariana', value:  StateRegion.Ariana },  
  { label: 'Beja', value:  StateRegion.Beja },
  { label: 'Bizerte', value:  StateRegion.Bizerte }, 
  { label: 'Ben Arous', value:  StateRegion.Ben_Arous },
  { label: 'Gabes', value:  StateRegion.Gabes },
  { label: 'Gafsa', value:  StateRegion.Gafsa }, 
  { label: 'Jendouba', value:  StateRegion.Jendouba },
  { label: 'Kairouan', value:  StateRegion.Kairouan }, 
  { label: 'Kasserine', value:  StateRegion.Kasserine },
  { label: 'Manouba', value:  StateRegion.Manouba },  
  { label: 'Kef', value:  StateRegion.Kef },
  { label: 'Mahdia', value:  StateRegion.Mahdia }, 
  { label: 'Medenine', value:  StateRegion.Medenine },
  { label: 'Monastir', value:  StateRegion.Monastir }, 
  { label: 'Nabeul', value:  StateRegion.Nabeul },
  { label: 'Sfax', value:  StateRegion.Sfax }, 
  { label: 'Sidi_Bouzid', value:  StateRegion.Sidi_Bouzid },
  { label: 'Siliana', value:  StateRegion.Siliana }, 
  { label: 'Sousse', value:  StateRegion.Sousse },
  { label: 'Tataouine', value:  StateRegion.Tataouine },  
  { label: 'Tozeur', value:  StateRegion.Tozeur },
  { label: 'Tunis', value:  StateRegion.Tunis },
  { label: 'Zaghouan', value:  StateRegion.Zaghouan}];


}
