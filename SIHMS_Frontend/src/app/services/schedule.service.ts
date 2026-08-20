import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { getscheduledclass, scheduleclass } from '../config/api-config';
import { EncryptService } from './encrypt.service';

@Injectable({
  providedIn: 'root'
})
export class ScheduleService {

  constructor(private http: HttpClient,private enctserv:EncryptService) { }

  getscheduledclass(fromdate: any, todate: any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
      params: {
        fromdate: fromdate,
        todate: todate
      }
    };
    let fullUrl =getscheduledclass;
    return this.http.get(fullUrl,options);
  }
  scheduleclass(object: any) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: this.enctserv.getJwtToken(),
      'Access-Control-Allow-Origin': '*',
    });
    let options = {
      headers: headers,
    };
    let fullUrl =scheduleclass;
    return this.http.post(fullUrl,object,options);
  }


}
