import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/front-office/services/offer-pro/company.service';
import { AccountService } from 'src/app/front-office/services/user/account.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  nameCompany:string = '';
  username:string = '';

   constructor(public companyService: CompanyService,public accountService: AccountService){ }

   ngOnInit(): void {
    this.nameCompany =  this.companyService.getCompanyDto().name;
    this.username =  this.accountService.getAccoutDto().userDto.username;
   }
}
