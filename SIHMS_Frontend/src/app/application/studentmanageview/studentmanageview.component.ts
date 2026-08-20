import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';
declare let $: any;


@Component({
  selector: 'app-studentmanageview',
  templateUrl: './studentmanageview.component.html',
  styleUrls: ['./studentmanageview.component.scss']
})
export class StudentmanageviewComponent {
  classlist:any=[];
  studentdata:any=[];

  constructor(private readonly studentserv:StudentService,
    private readonly router:Router,
    private readonly enctserv:EncryptService){};

  ngOnInit(): void {
    this.getclassroomdata();
  }

  getclassroomdata(){
    this.studentserv.getClassroomdetails().subscribe((data:any) => {
      if(data.status == 200){
        this.classlist = data.data;
      } else {
        Swal.fire("Error", "Something went wrong","error")
      }
    });
  }

  getstudentdata(classno:any){
    $("#studentdetails").show();
    this.studentserv.getstudentdata(classno).subscribe((data:any) => {
      if(data.status == 200){
        this.studentdata = data.data;
      } else {
        Swal.fire("Error", "Something went wrong","error")
      }
    });
  }

  closemodal(){
    $("#studentdetails").hide();
    this.studentdata=[];
  }
}
