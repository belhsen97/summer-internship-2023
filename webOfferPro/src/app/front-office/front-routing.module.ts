import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from '../vitrine/layout/layout.component';
import { EditGlobalAccountComponent } from './user/account-component/edit-global-account/edit-global-account.component';
import { FormForgotPasswordComponent } from './user/user-component/form-forgot-password/form-forgot-password.component';
import { UpdatePasswordForgotComponent } from './user/user-component/update-password-forgot/update-password-forgot.component';
import { ProfileAccountComponent } from './user/account-component/profile-account/profile-account.component';
import { ComposeComponent } from './mail/mail-component/compose/compose.component';
import { DetailsAccountComponent } from './user/account-component/details-account/details-account.component';
import { DetailsCompanyComponent } from './offer-pro/company-component/details-company/details-company.component';
import { EditCompanyComponent } from './offer-pro/company-component/edit-company/edit-company.component';
import { ListAccountComponent } from './user/account-component/list-account/list-account.component';
import { DetailedRecruitmentOfferComponent } from './offer-pro/recruitment-offer-component/detailed-recruitment-offer/detailed-recruitment-offer.component';
import { SearchRecruitmentOfferComponent } from './offer-pro/recruitment-offer-component/search-recruitment-offer/search-recruitment-offer.component';
const routes: Routes = [
  {
    path: 'user/account',
    component: LayoutComponent,
    children: [
      {
        path: 'update-profile',
        component: EditGlobalAccountComponent
      }
    ]
  },
  {
    path: 'list',
    component: LayoutComponent,
    children: [
      {
        path: 'profiles',
        component: ListAccountComponent
      }
    ]
  },
  {
    path: 'user',
    component: LayoutComponent,
    children: [
      {
        path: 'profile/:username',
        component: ProfileAccountComponent
      }
    ]
  },
  { 
        path: 'forgot-password',
        component: FormForgotPasswordComponent   
  }
  ,
  { 
        path: 'update-password-forgot/:code',
        component: UpdatePasswordForgotComponent   
  },
  {
    path: 'email',
    component: LayoutComponent,
    children: [
      {
        path: 'compose',
        component: ComposeComponent
      }
    ]
  },
  {
    path: 'company',
    component: LayoutComponent,
    children: [
      {
        path: 'details',  //http://localhost:4200/company/details?Name=ST
        component: DetailsCompanyComponent //<a [routerLink]="['/company/details']" [queryParams]="{ Name: 'ST' }">Company ST</a>
      },
      {
        path: 'edit',
        component: EditCompanyComponent
      }
    ]
  }
  ,
  {
    path: 'recruitment-offer',
    component: LayoutComponent,
    children: [
      {
        path: 'detailed',   //http://localhost:4200/recruitment-offer/detailed?id=1&title=title-1
        component: DetailedRecruitmentOfferComponent 
      },
      {
        path: 'search',   //http://localhost:4200/recruitment-offer/search?key=ABC
        component: SearchRecruitmentOfferComponent 
      }
    ]
  }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes) /*CommonModule*/ ]
  ,exports: [RouterModule]
})
export class FrontRoutingModule { }
