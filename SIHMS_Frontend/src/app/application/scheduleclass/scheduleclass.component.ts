import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import { ScheduleService } from 'src/app/services/schedule.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';
declare let $: any;

@Component({
  selector: 'app-scheduleclass',
  templateUrl: './scheduleclass.component.html',
  styleUrls: ['./scheduleclass.component.scss']
})
export class ScheduleclassComponent {
  classlist:any=[];
  scheduledclass:any=[];

  constructor(private readonly studentserv:StudentService,
    private readonly schduleserv:ScheduleService,
    private readonly router:Router,
    private readonly enctserv:EncryptService){};

  ngOnInit(): void {
    this.getclassroomdata();
    this.search();
  }

  getclassroomdata(){
      this.studentserv.getClassroom().subscribe((data:any) => {
        this.classlist = data.data;
      });
  }

  submit(){
    let classNo = $('#class').val();
    let date = $('#submitdate').val();
    let fromtime = $('#fromtime').val();
    let totime = $('#totime').val();

    if (classNo==null || classNo== "" || classNo==undefined){
      Swal.fire("Error","Please Enter Class","error");
      $('#class').focus();
      return;
    }

    if (date==null || date== "" || date==undefined){
      Swal.fire("Error","Please Enter Date","error");
      $('#submitdate').focus();
      return;
    }

    if (fromtime==null || fromtime== "" || fromtime==undefined){
      Swal.fire("Error","Please Enter From time","error");
      $('#fromtime').focus();
      return;
    }

    if (totime==null || totime== "" || totime==undefined){
      Swal.fire("Error","Please Enter To time","error");
      $('#totime').focus();
      return;
    }

    Swal.fire({
      title: 'Are you sure?',
      text: 'You want to Schedule it ?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.isConfirmed) {
        let object= {
          "classNo":classNo,
          "date":date,
          "fromtime":fromtime,
          "totime":totime,
        }
        this.schduleserv.scheduleclass(object).subscribe((result:any)=>{
          if(result.status == 200){
            Swal.fire({
              position: "center",
              icon: "success",
              title: "Your details have been saved.",
              showConfirmButton: false,
              timer: 1500
            });
            this.search();
          }else{
            Swal.fire('Failed!', 'Failed to save your details. Please try again.', 'error');
          }
        },
          (error:any)=>console.log(error)
        );
      }
    });

  }

  resetVal(){
    window.location.reload();
  }

  search(){
    let fromdate = $('#fromdate').val();
    let todate = $('#todate').val();

    // if (fromdate==null || fromdate== "" || fromdate==undefined){
    //   Swal.fire("Error","Please Enter From Date","error");
    //   $('#fromdate').focus();
    //   return;
    // }

    // if (todate==null || todate== "" || todate==undefined){
    //   Swal.fire("Error","Please Enter To Date","error");
    //   $('#todate').focus();
    //   return;
    // }

    this.schduleserv.getscheduledclass(fromdate,todate).subscribe((result:any)=>{
      if(result.status == 200){
        this.scheduledclass = result.data;
      }else{
        Swal.fire('Failed!', 'Failed to save your details. Please try again.', 'error');
      }
    });
  }

}
