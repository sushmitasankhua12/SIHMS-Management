import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import Swal from 'sweetalert2';
declare let $ : any;

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  notifications: any=[];
  user:any;
  userhousedatalist:any=[];

  constructor(private router:Router,private location:Location,
              private readonly enctserv:EncryptService) { }

  ngOnInit(): void {
    let userdata:any=sessionStorage.getItem('user');
    this.user=JSON.parse(userdata);
    if(this.user.groupId == 1){
      this.notifications = [
        { message: "New Homework Assigned! You have successfully assigned homework for 26-Mar-2001" },
        { message: "Today Class 9:30 Am to 12:30 Pm for calss 8." },
        { message: "Parent Raised Some Request ." },
        { message: "All Student Submit their Homework ." }
      ];
    }else{
      this.notifications = [
        { message: "New Homework Assigned! Please check your account for details and complete it by 26-Mar-2001." },
        { message: "Your homework is due on 26-Mar-2001. Make sure to submit it on time!" },
        { message: "Homework Deadline Approaching! Submit your work before the due date to avoid penalties." },
        { message: "A new homework assignment has been added. Check their progress now!" }
      ];
    }
  }

  back(){
    this.location.back();
  }

  logout(){
    sessionStorage.clear();
    this.router.navigate(['/login']);
  }

  closemodal(){
    $('#changepassword').hide();
    this.sentotp=false;
    $('#newpass').val('');
    $('#cnfpass').val('');
    $('#otpval').val('');
  }

  changePassword(){
    $('#changepassword').show();
  }

  sentotp:boolean = false;
  attemptcount:any=5;

  referesh(){
    window.location.reload();
  }

  sendOTP(){

  }

  verifyOTP(){

  }

}
