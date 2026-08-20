import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthgardService implements CanActivate{
  constructor(private router: Router ) {

  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    let userdata:any=sessionStorage.getItem('user');
    const user=JSON.parse(userdata);
    if (user) {
      return true;
    } else {
      this.router.navigate(['/']);
      this.router.navigate(['/unauthorize'], { queryParams: { unAuthrized: state.url } });
      return false;
    }
  }
}
