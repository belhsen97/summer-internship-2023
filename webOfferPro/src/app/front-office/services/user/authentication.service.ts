import { HttpClient,HttpResponse,HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AccountDto } from 'src/app/models/user_model/AcountDto';
import { AuthenticationRequestDto } from 'src/app/models/user_model/AuthenticationRequestDto';
import { AuthenticationResponseDto } from 'src/app/models/user_model/AuthenticationResponseDto';
import { MsgReponseStatusDto } from 'src/app/models/user_model/MsgReponseStatusDto';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { StateRegion } from 'src/app/models/user_model/StateRegion';
import { environment } from 'src/environments/environment';
import { AccountService } from './account.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  url = `${environment.apiBaseUrl}`;
  authRequestDto : AuthenticationRequestDto = new AuthenticationRequestDto();
  authResponseDto : AuthenticationResponseDto = new AuthenticationResponseDto();
  msgReponseStatusDto  : MsgReponseStatusDto = new MsgReponseStatusDto(); 

  readonly  SUCCESSFUL :ReponseStatus = ReponseStatus.SUCCESSFUL;
  readonly  ERROR:ReponseStatus = ReponseStatus.ERROR;
  readonly  UNSUCCESSFUL:ReponseStatus = ReponseStatus.UNSUCCESSFUL;


  
  constructor(private accountService : AccountService ,
              private http:HttpClient,
              private router: Router,
              private activeRoute: ActivatedRoute){ }
    goToComponent(component:string) : void {this.router.navigateByUrl(component);} // eq de routerLink="child1"
    register(authRequestDto:AuthenticationRequestDto) : Observable<HttpResponse<any>> {
      return this.http.post(`${this.url}/user-service/user/register`,
      authRequestDto ,
      {observe : 'response',  headers: new HttpHeaders({ 'Content-Type': 'application/json' })})
    }
    login(authRequest:AuthenticationRequestDto) : Observable<HttpResponse<any>> {
      return this.http.post(`${this.url}/user-service/authentication/authenticate`,authRequest , {observe : 'response'
      ,  headers: new HttpHeaders({ 'Content-Type': 'application/json' })})
    }  
    saveLogin(token:string,username:string):void{
      this.accountService.getByUsername (username).subscribe(
        (response) => { 
          this.accountService.accountDto =  response.body;
          this.setToken(token);
          this.accountService.setAccountDto( this.accountService.accountDto);
          this.goToComponent("user/profile");
         }
      ,(error) => {   console.log( error )  }) ;
    }
    logout() : Observable<HttpResponse<any>> { 
      return this.http.post(`${this.url}/user-service/authentication/logout` ,{}, {observe : 'response'
      ,  headers: new HttpHeaders({ 'Authorization': "Bearer " +this. getToken() })     }  )
    }
    updatePassword( username:string, currentPassword:string,newPassword:string ) : Observable<HttpResponse<any>> {
      return this.http.put(`${this.url}/user-service/user/update-password/${username}/${currentPassword}/${newPassword}` ,{}, 
      {observe : 'response'})
    }

  
   clearAll() :void{this. clearTokon();this.accountService.clearAccoutDto(); }
   setToken(token:string) :void{  localStorage.removeItem('Authorization');localStorage.setItem('Authorization',token);}
   getToken() : string{
    const content =   localStorage.getItem('Authorization'); 
    const token = (  content == null  ||  content == ""  ?  "" :  content  ) ;
    return token;}
   clearTokon() : void{localStorage.removeItem( 'Authorization');}
}
