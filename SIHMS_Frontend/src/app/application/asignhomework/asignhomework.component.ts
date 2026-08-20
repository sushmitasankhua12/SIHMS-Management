import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import { StudentService } from 'src/app/services/student.service';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { ChangeEvent } from '@ckeditor/ckeditor5-angular/ckeditor.component';
import Swal from 'sweetalert2';
declare let $: any;

@Component({
  selector: 'app-asignhomework',
  templateUrl: './asignhomework.component.html',
  styleUrls: ['./asignhomework.component.scss']
})
export class AsignhomeworkComponent {
  classlist:any=[];
  public Editor = ClassicEditor;
  editorvalue:any;
  classwisehomework:any=[];

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

  public model = {
    editorData:""
  };

  public config = {
    placeholder: 'Type the content here!'
  }

  onChange(event: any) {
    this.editorvalue = event.editor.getData(); // Get updated content
  }

  submit(){
    let classNo = $('#class').val();
    let date = $('#submitdate').val();
    let homework = $('#homework').val();

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
          homework:homework,
        }
        this.studentserv.asignhomework(object).subscribe((result:any)=>{
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
