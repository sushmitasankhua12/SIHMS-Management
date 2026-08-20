import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { addstudent,asignhomework,asignindividulahomework,getasignhomework,getClassroom,getClassroomdetails,getstudentdata, getstudentdatafordashbord, getteacherdata } from 'src/app/config/api-config';
import { EncryptService } from 'src/app/services/encrypt.service';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  

  getClassroom() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =getClassroom;
    return this.http.get(fullUrl,options);
  }

  getClassroomdetails() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =getClassroomdetails;
    return this.http.get(fullUrl,options);
  }

  getstudentdata(classno:any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
      params:{
        classNo:classno
      }
    };
    let fullUrl =getstudentdata;
    return this.http.get(fullUrl,options);
  }

  constructor(private http: HttpClient,private enctserv:EncryptService) { }

  addstudent(formData: any) {
    let headers = new HttpHeaders({
      // 'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =addstudent;
    return this.http.post(fullUrl,formData,options);
  }

  asignhomework(object:any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =asignhomework;
    return this.http.post(fullUrl,object,options);
  }

  asignindividulahomework(object: any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =asignindividulahomework;
    return this.http.post(fullUrl,object,options);
  }

  getasignhomework(object: any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =getasignhomework;
    return this.http.post(fullUrl,object,options);
  }

  getteacherdata() {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =getteacherdata;
    return this.http.get(fullUrl,options);
  }

  getstudentdatafordashbord(userId: any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
      params:{
        userId:userId
      }
    };
    let fullUrl =getstudentdatafordashbord;
    return this.http.get(fullUrl,options);
  }
}
