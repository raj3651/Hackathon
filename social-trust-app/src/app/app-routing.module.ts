import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ApplicantListComponent} from "./components/applicant-list/applicant-list.component";

const routes: Routes = [
  {path: "applicants", component: ApplicantListComponent},
  {path: '', redirectTo: '/applicants', pathMatch: 'full',}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
