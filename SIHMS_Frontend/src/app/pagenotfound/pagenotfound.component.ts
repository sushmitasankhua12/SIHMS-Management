import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pagenotfound',
  templateUrl: './pagenotfound.component.html',
  styleUrls: ['./pagenotfound.component.scss']
})
export class PagenotfoundComponent {
  constructor(public Route:Router) { }

  logout() {
    sessionStorage.clear();
    this.Route.navigateByUrl('/login');
  }

}
