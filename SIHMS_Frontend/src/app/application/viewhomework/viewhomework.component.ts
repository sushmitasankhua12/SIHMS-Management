import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';
declare let $: any;

@Component({
  selector: 'app-viewhomework',
  templateUrl: './viewhomework.component.html',
  styleUrls: ['./viewhomework.component.scss']
})
export class ViewhomeworkComponent {
  classlist:any=[];
  studentlist:any=[];
  homeworklist:any=[];
  
    constructor(private readonly studentserv:StudentService,
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
  
    onChangeClass($event:any){
        this.studentserv.getstudentdata($event.target.value).subscribe((data:any) => {
          if(data.status == 200){
            this.studentlist = data.data;
          } else {
            Swal.fire("Error", "Something went wrong","error")
          }
        });
      }
  search(){
    let classNo = $('#class').val();
    let student = $('#student').val();

    let object = {
      classNo:classNo,
      studentId:student
    }
    this.studentserv.getasignhomework(object).subscribe((result:any)=>{
      if(result.status == 200){
        this.homeworklist = result.data;
      }else{
        Swal.fire('Failed!', 'Failed to save your details. Please try again.', 'error');
      }
    });
  }

}
