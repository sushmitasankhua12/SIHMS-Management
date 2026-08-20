import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { PagenotfoundComponent } from './pagenotfound/pagenotfound.component';
import { AuthgardService } from './services/authgard.service';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full'  },
  { path: 'login', component: LoginpageComponent },
  { path: 'application', loadChildren: () => import('./application/application.module').then(m => m.ApplicationModule),canActivate: [AuthgardService] },
  { path: '**', component: PagenotfoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
