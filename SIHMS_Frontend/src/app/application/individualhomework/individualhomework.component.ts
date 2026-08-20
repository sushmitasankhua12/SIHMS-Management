import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';
declare let $: any;


@Component({
  selector: 'app-individualhomework',
  templateUrl: './individualhomework.component.html',
  styleUrls: ['./individualhomework.component.scss']
})
export class IndividualhomeworkComponent {
  classlist:any=[];
  studentlist:any=[];

  constructor(private readonly studentserv:StudentService,
    private readonly router:Router,
    private readonly enctserv:EncryptService){};

  ngOnInit(): void {
    this.getclassroomdata();
  }

  getclassroomdata(){
      this.studentserv.getClassroom().subscribe((data:any) => {
        this.classlist = data.data;
      });
  }

  onChangeClass($event:any){
      this.studentserv.getstudentdata($event.target.value).subscribe((data:any) => {
        if(data.status == 200){
          this.studentlist = data.data;
        } else {
          Swal.fire("Error", "Something went wrong","error")
        }
      });
    }

  submit(){
    let classNo = $('#class').val();
    let student = $('#student').val();
    let date = $('#submitdate').val();
    let homework = $('#homework').val();

    if (classNo==null || classNo== "" || classNo==undefined){
      Swal.fire("Error","Please Enter Class","error");
      $('#class').focus();
      return;
    }

    if (student==null || student== "" || student==undefined){
      Swal.fire("Error","Please Student Name","error");
      $('#student').focus();
      return;
    }

    if (date==null || date== "" || date==undefined){
      Swal.fire("Error","Please Enter Date","error");
      $('#submitdate').focus();
      return;
    }

    if (homework==null || homework== "" || homework==undefined){
      Swal.fire("Error","Please Enter Home Work/ Assignment","error");
      $('#homework').focus();
      return;
    }

    Swal.fire({
      title: 'Are you sure?',
      text: 'You want to Asign Task ?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.isConfirmed) {
        let object = {
          classNo:classNo,
          dueDate:date,
          studentId:student,
          homework:homework,
        }
        this.studentserv.asignindividulahomework(object).subscribe((result:any)=>{
          if(result.status == 200){
            Swal.fire({
              position: "center",
              icon: "success",
              title: "Your details have been saved.",
              showConfirmButton: false,
              timer: 1500
            });
            this.router.navigate(['/application/viewHomework']);
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
}
