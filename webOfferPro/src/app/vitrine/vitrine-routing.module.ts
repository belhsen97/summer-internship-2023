import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { SignInComponent } from './sign-in/sign-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LayoutComponent } from './layout/layout.component';
import { PageErrorComponent } from './page-error/page-error.component';
import { SuccessSignUpComponent } from './success-sign-up/success-sign-up.component';

const routes: Routes = [
  {
    path: 'home',
    component: LayoutComponent,
    children: [
      {
        path: '',
        component: HomeComponent
      }
    ]
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
  },
  {
    path: 'sign-in',
    component: SignInComponent,
  },
  {
    path: 'page-error',
    component: PageErrorComponent,
  },
  {
    path: 'success-sign-up/:email',
    component: SuccessSignUpComponent,
  } 
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes) /*CommonModule*/ ]
  ,exports: [RouterModule]
})



export class VitrineRoutingModule { }
