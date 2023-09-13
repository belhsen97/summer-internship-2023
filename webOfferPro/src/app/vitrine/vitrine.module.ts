import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VitrineRoutingModule } from './vitrine-routing.module';
import { FormsModule } from '@angular/forms';

import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LayoutComponent } from './layout/layout.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { FooterComponent } from './footer/footer.component';
import { PageErrorComponent } from './page-error/page-error.component';
import { MessageBoxConfirmationStandartComponent } from './message-box-confirmation-standart/message-box-confirmation-standart.component';
import { MessageBoxUploadImgComponent } from './message-box-upload-img/message-box-upload-img.component';
import { MessageBoxResponseComponent } from './message-box-response/message-box-response.component';
import { SuccessSignUpComponent } from './success-sign-up/success-sign-up.component';



@NgModule({
  declarations: [
    HomeComponent,
    NavbarComponent,
    SignInComponent,
    SignUpComponent,
    LayoutComponent,
    SidebarComponent,
    FooterComponent,
    PageErrorComponent,
    MessageBoxConfirmationStandartComponent,
    MessageBoxUploadImgComponent,
    MessageBoxResponseComponent,
    SuccessSignUpComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    VitrineRoutingModule
  ],
  exports: [MessageBoxConfirmationStandartComponent,MessageBoxUploadImgComponent,MessageBoxResponseComponent]
})
export class VitrineModule { }
