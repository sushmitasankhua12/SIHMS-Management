import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ApplicationRoutingModule } from './application-routing.module';
import { FacaultiDashbordComponent } from './facaulti-dashbord/facaulti-dashbord.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorInterceptor } from '../services/error.interceptor';
import { HeaderComponent } from './header/header.component';
import { StudentmanageComponent } from './studentmanage/studentmanage.component';
import { StudentmanageviewComponent } from './studentmanageview/studentmanageview.component';
import { AsignhomeworkComponent } from './asignhomework/asignhomework.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { FormsModule } from '@angular/forms';
import { ScheduleclassComponent } from './scheduleclass/scheduleclass.component';
import { IndividualhomeworkComponent } from './individualhomework/individualhomework.component';
import { ViewhomeworkComponent } from './viewhomework/viewhomework.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FacaultiDashbordComponent,
    StudentmanageComponent,
    StudentmanageviewComponent,
    AsignhomeworkComponent,
    ScheduleclassComponent,
    IndividualhomeworkComponent,
    ViewhomeworkComponent,
  ],
  imports: [
    CommonModule,
    CKEditorModule,FormsModule,
    ApplicationRoutingModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ]
})
export class ApplicationModule { }
