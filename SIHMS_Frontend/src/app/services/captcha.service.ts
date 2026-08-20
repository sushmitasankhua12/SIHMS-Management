import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CaptchaService {

  constructor() { }

  verifyCaptcha(): boolean {
    return true;
  }

  public getCaptcha() {
    let alpha = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
      // 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
      let letter= new Array('1', '2', '3', '4', '5', '6', '7', '8', '9');
      let sign=new Array('+','-','*')
      let letter1= new Array('1', '2', '3', '4', '5', '6', '7', '8', '9');
      let i,code='';

            /* for Alphabate Captcha>*/
    // for (i = 0; i < 6; i++) {
    //   let a1 = alpha[Math.floor(Math.random() * alpha.length)];
    //   let b1 = alpha[Math.floor(Math.random() * alpha.length)];
    //   let c1 = alpha[Math.floor(Math.random() * alpha.length)];
    //   let d1 = alpha[Math.floor(Math.random() * alpha.length)];
    //   let e1 = alpha[Math.floor(Math.random() * alpha.length)];
    //   let f1 = alpha[Math.floor(Math.random() * alpha.length)];
    //   let g1 = alpha[Math.floor(Math.random() * alpha.length)];
    //    code = a1 + ' ' + b1 + ' ' + ' ' + c1 + ' ' + d1 + ' ' + e1 + ' ' + f1 + ' ' + g1 ;
    // }

     /* for Mathematical Captcha>*/
     /*@Created By Rajendra.sahoo*/
    let b=true;
     while(b){
      let l1 = letter[Math.floor(Math.random() * letter.length)];
          let s1 = sign[Math.floor(Math.random() * sign.length)];
          let l2 = letter1[Math.floor(Math.random() * letter1.length)];
            code=l1 +' ' + s1 +' ' + l2
          let ans=this.calculatecaptcha(code);
          if(ans>=0){
            b=false;
          }else{
            b=true;
          }
     }
    return code;
  }

  public validateCaptcha(challange: any, captcha: string): boolean {
    console.log("validating captcha");
    challange = this.removeSpaces(challange);
    // captcha=this.removeSpaces(captcha);
    let ans = this.calculatecaptcha(captcha);
    if (challange == ans) {
      return true;
    } else {
      return false;
    }
  }
  removeSpaces(string: string) {
    return string.split(' ').join('');
  }

  public calculatecaptcha(string: string){
    let s:any[]=string.split(' ');
    let s1:number=parseInt(s[0]);
    let s2:any=s[1];
    let s3:number=parseInt(s[2]);
    if(s2=='+'){
      return s1+s3;
    }else if(s2=='-'){
      return s1-s3;
    }else if (s2=='*'){
      return s1*s3;
    }else{
      return 0;
    }
  }
}



