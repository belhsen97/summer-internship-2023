import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { CommonModule } from '@angular/common';
import { EditGlobalAccountComponent } from './user/account-component/edit-global-account/edit-global-account.component';
import { FrontRoutingModule } from './front-routing.module';
import { ListAccountComponent } from './user/account-component/list-account/list-account.component';
import { DetailsAccountComponent } from './user/account-component/details-account/details-account.component';
import { UploadImgAccountComponent } from './user/account-component/upload-img-account/upload-img-account.component';
import { VitrineModule } from '../vitrine/vitrine.module';
import { FormForgotPasswordComponent } from './user/user-component/form-forgot-password/form-forgot-password.component';
import { UpdatePasswordForgotComponent } from './user/user-component/update-password-forgot/update-password-forgot.component';
import { ProfileAccountComponent } from './user/account-component/profile-account/profile-account.component';
import { ComposeComponent } from './mail/mail-component/compose/compose.component';
import { DetailsCompanyComponent } from './offer-pro/company-component/details-company/details-company.component';
import { EditCompanyComponent } from './offer-pro/company-component/edit-company/edit-company.component';
import { AddRecruitmentOfferComponent } from './offer-pro/recruitment-offer-component/add-recruitment-offer/add-recruitment-offer.component';

import { PositiveNumberValidatorDirective } from '../libraries/PositiveNumberValidatorDirective';
import { ExperienceRangeValidatorDirective } from '../libraries/ExperienceRangeValidatorDirective';
import { DateNowValidatorDirective } from '../libraries/DateNowValidatorDirective';
import { PostRecruitmentOfferComponent } from './offer-pro/recruitment-offer-component/post-recruitment-offer/post-recruitment-offer.component';
import { DetailedRecruitmentOfferComponent } from './offer-pro/recruitment-offer-component/detailed-recruitment-offer/detailed-recruitment-offer.component';
import { AddApplyOfferComponent } from './offer-pro/apply-on-offer-component/add-apply-offer/add-apply-offer.component';
import { AddFormComponent } from './offer-pro/Form-component/add-form/add-form.component';
import { SearchRecruitmentOfferComponent } from './offer-pro/recruitment-offer-component/search-recruitment-offer/search-recruitment-offer.component';



@NgModule({
  declarations: [
    EditGlobalAccountComponent,
    ListAccountComponent,
    DetailsAccountComponent,
    UploadImgAccountComponent,
    FormForgotPasswordComponent,
    UpdatePasswordForgotComponent,
    ProfileAccountComponent,
    ComposeComponent,
    DetailsCompanyComponent,
    EditCompanyComponent,
    AddRecruitmentOfferComponent,

    PositiveNumberValidatorDirective,
    DateNowValidatorDirective,
    ExperienceRangeValidatorDirective,
    PostRecruitmentOfferComponent,
    DetailedRecruitmentOfferComponent,
    AddApplyOfferComponent,
    AddFormComponent,
    SearchRecruitmentOfferComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    FrontRoutingModule
    ,VitrineModule
  ]
})
export class FrontOfficeModule { }
