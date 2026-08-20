import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { CaptchaService } from '../services/captcha.service';
import { EncryptService } from '../services/encrypt.service';
import { LoginserviceService } from '../services/loginservice.service';
declare let $: any;

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.scss']
})
export class LoginpageComponent {
  rslt:any;

  constructor(private readonly captchaService:CaptchaService,
    private readonly leginsrv:LoginserviceService,
    private readonly router:Router,private readonly route:ActivatedRoute,
    private readonly enctserv:EncryptService){};

  ngOnInit(): void {
    this.captref();
  }

  captref(){
    let component = this;
    $('#loginCaptchaImg').html(this.captchaService.getCaptcha());
    $('#loginRefreshCaptcha').click(function () {
      $('#loginCaptchaImg').html(component.captchaService.getCaptcha());
    });
  }

  onLoggedIn(){
    let challange = $('#capt').val();
    let captcha = $('#loginCaptchaImg').html();
    let isValid: boolean;
    isValid = this.captchaService.validateCaptcha(challange, captcha);
    if(!isValid){
      Swal.fire("Error","InCorrect Captcha","error");
      return;
    }
    let username=$('#username').val();
    let password=$('#password').val();
    if (username==null || username== "" || username==undefined){
      Swal.fire("Error","Please Fill UserName","error");
      return;
    }
    if (password==null || password== "" || password==undefined){
      Swal.fire("Error","Please Fill Password","error");
        return;
    }
    username=this.enctserv.OnEncrypt(username);
    password=this.enctserv.OnEncrypt(password);
    this.leginsrv.login(username,password).subscribe((data:any)=>{
      this.rslt=data;
      if(this.rslt.status==200){
        sessionStorage.setItem('user', JSON.stringify(this.rslt.userdata));
        sessionStorage.setItem('token', this.rslt.token);
        this.router.navigate(['/application/facaultiDashbord']);
      }else if(this.rslt.status==400){
        Swal.fire("Error",this.rslt.message,"error");
        return;
      }else if(this.rslt.status==404){
        Swal.fire("Error","User Not Found !!","error");
        return;
      }else{
        Swal.fire("Error","Something Went Wrong !","error");
        return;
      }
    });
  }

  sendOTP(){

  }

  verifyOTP(){

  }

  sentotp:boolean = false;
  attemptcount:any=5;
  closemodal(){
    $('#changepassword').hide();
    $('#newpass').val('');
    $('#cnfpass').val('');
    $('#otpval').val('');
  }



}
