import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AsignhomeworkComponent } from './asignhomework/asignhomework.component';
import { FacaultiDashbordComponent } from './facaulti-dashbord/facaulti-dashbord.component';
import { IndividualhomeworkComponent } from './individualhomework/individualhomework.component';
import { ScheduleclassComponent } from './scheduleclass/scheduleclass.component';
import { StudentmanageComponent } from './studentmanage/studentmanage.component';
import { StudentmanageviewComponent } from './studentmanageview/studentmanageview.component';
import { ViewhomeworkComponent } from './viewhomework/viewhomework.component';

const routes: Routes = [
  { path: 'facaultiDashbord', component: FacaultiDashbordComponent },
  { path: 'Studentmanage', component: StudentmanageComponent },
  { path: 'Studentmanageview', component: StudentmanageviewComponent },
  { path: 'assignHomework', component: AsignhomeworkComponent },
  { path: 'individualHomework', component: IndividualhomeworkComponent },
  { path: 'viewHomework', component: ViewhomeworkComponent },
  { path: 'scheduleclass', component: ScheduleclassComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ApplicationRoutingModule { }
