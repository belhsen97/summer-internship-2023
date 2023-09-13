import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/front-office/services/user/account.service'; 
import { AuthenticationService } from 'src/app/front-office/services/user/authentication.service';
import { ReponseStatus } from 'src/app/models/user_model/ReponseStatus';
import { AttachementDto } from 'src/app/models/user_model/AttachementDto';
import { Category } from 'src/app/models/user_model/Category';
import { AccountDto } from 'src/app/models/user_model/AcountDto';

@Component({
  selector: 'app-list-account',
  templateUrl: './list-account.component.html',
  styleUrls: ['./list-account.component.css']
})
export class ListAccountComponent implements OnInit {

  constructor(public accountService : AccountService,
              public authService : AuthenticationService ) {}
  ngOnInit(): void { 
   this.accountService.getAll().subscribe(
      (response) => {
        const listAccounts : AccountDto[] = response.body;
        this.accountService.ListAccounts = listAccounts;
        console.log(  this.accountService.ListAccounts );
       }
      ,(error) => {
        console.log( error.message);
       /* this.stateMsgBoxAuth = true ; 
        this.authService.msgReponseStatusDto =  
        { title : "Error", datestamp: new Date() , timestamp: new Date(),status : ReponseStatus.ERROR , message : error.message};*/
      }) ;
  }

}
