import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EncryptService } from 'src/app/services/encrypt.service';
import { StudentService } from 'src/app/services/student.service';
import Swal from 'sweetalert2';
declare let $: any;

@Component({
  selector: 'app-studentmanage',
  templateUrl: './studentmanage.component.html',
  styleUrls: ['./studentmanage.component.scss']
})
export class StudentmanageComponent {
  classlist:any=[];

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

  submit(){
    let fullname = $('#fullname').val();
    let mobile = $('#mobile').val();
    let mailid = $('#mailid').val();
    let pfullname = $('#pfullname').val();
    let pmobile = $('#pmobile').val();
    let pmailid = $('#pmailid').val();
    let address = $('#address').val();
    let classno = $('#class').val();

    if (fullname==null || fullname== "" || fullname==undefined){
      Swal.fire("Error","Please Enter Student Name","error");
      $('#fullname').focus();
      return;
    }

    if (mobile==null || mobile== "" || mobile==undefined){
      Swal.fire("Error","Please Enter Student Mobile No","error");
      $('#mobile').focus();
      return;
    }

    if (mailid==null || mailid== "" || mailid==undefined){
      Swal.fire("Error","Please Enter Mail Id","error");
      $('#mailid').focus();
      return;
    }

    if (pfullname==null || pfullname== "" || pfullname==undefined){
      Swal.fire("Error","Please Enter Parent FullName","error");
      $('#pfullname').focus();
      return;
    }

    // if (pmobile==null || pmobile== "" || pmobile==undefined){
    //   Swal.fire("Error","Please Enter Parent Mobile No","error");
    //   $('#pmobile').focus();
    //   return;
    // }

    // if (pmailid==null || pmailid== "" || pmailid==undefined){
    //   Swal.fire("Error","Please Enter Parent Mail Id","error");
    //   $('#pmailid').focus();
    //   return;
    // }

    if (address==null || address== "" || address==undefined){
      Swal.fire("Error","Please Enter Address","error");
      $('#address').focus();
      return;
    }

    if (classno==null || classno== "" || classno==undefined){
      Swal.fire("Error","Please Select Class","error");
      $('#classno').focus();
      return;
    }

    let object = {
      'studentName': fullname,
      'phoneNo': mobile,
      'mailId': mailid,
      'parentName': pfullname,
      'parentMobile': pmobile,
      'parentMail': pmailid,
      'address': address,
      'classNo': classno,
    }

    Swal.fire({
      title: 'Are you sure?',
      text: 'You want to add Student ?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonText: 'Yes',
      cancelButtonText: 'No'
    }).then((result) => {
      if (result.isConfirmed) {
        this.studentserv.addstudent(object).subscribe((result:any)=>{
          if(result.status == 200){
            Swal.fire({
              position: "center",
              icon: "success",
              title: "Your details have been saved.",
              showConfirmButton: false,
              timer: 1500
            });
            this.router.navigate(['/application/Studentmanageview']);
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
