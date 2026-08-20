import { Injectable } from '@angular/core';
import {loginUrl} from '../config/api-config'
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {


  constructor(private http: HttpClient) { }

  login(username:any,password:any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let object ={
      "username":username,
      "passWord":password
    }
    let fullUrl =loginUrl;
    return this.http.post(fullUrl,object,options)
  }
}
