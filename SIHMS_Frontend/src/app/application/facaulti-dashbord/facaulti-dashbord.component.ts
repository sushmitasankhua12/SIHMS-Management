import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-facaulti-dashbord',
  templateUrl: './facaulti-dashbord.component.html',
  styleUrls: ['./facaulti-dashbord.component.scss']
})
export class FacaultiDashbordComponent {
  scheduleclass:any=[];
  homework:any=[];
  user:any;

  constructor(private readonly studentserv:StudentService,
      private readonly router:Router,
      private readonly enctserv:EncryptService){};

    ngOnInit(): void {
      let userdata:any=sessionStorage.getItem('user');
      this.user=JSON.parse(userdata);
      if(this.user.groupId == 1){
        this.getteacherdata();
      }else{
        this.getstudentdata();
      }
  }
  getstudentdata() {
    this.studentserv.getstudentdatafordashbord(this.user.userId).subscribe((data:any) => {
      if(data.status == 200){
        this.scheduleclass = data.classdata;
        this.homework = data.homeworkdata;
      } else {
        Swal.fire("Error", "Something went wrong","error")
      }
    });
  }
  getteacherdata() {
    this.studentserv.getteacherdata().subscribe((data:any) => {
      if(data.status == 200){
        this.scheduleclass = data.data;
      } else {
        Swal.fire("Error", "Something went wrong","error")
      }
    });
  }
}
