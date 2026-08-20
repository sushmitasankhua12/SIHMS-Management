import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EncryptService {

  constructor() { }

  OnEncrypt(normalPass:any) {
    if(normalPass)
      return this.makeRandom() + btoa(normalPass) + this.makeRandom();
    else
      return normalPass
  }


  OnDecrypt(normalPass:any) {
    if(normalPass){
      normalPass = normalPass.substring(5, normalPass.length - 5);
      return atob(normalPass);
    }
    else
      return normalPass;
  }

  makeRandom() {
    let text = "";
    let possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for (let i = 0; i < 5; i++)
      text += possible.charAt(Math.floor(Math.random() * possible.length));
    return text;
  }

  getJwtToken() {
    let token:any=sessionStorage.getItem('token');
    return token;
  }

}
