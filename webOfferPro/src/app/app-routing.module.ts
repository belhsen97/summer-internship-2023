import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./vitrine/vitrine.module').then(m => m.VitrineModule) },
  { path: '', loadChildren: () => import('./front-office/front-office.module').then(m => m.FrontOfficeModule) }
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
