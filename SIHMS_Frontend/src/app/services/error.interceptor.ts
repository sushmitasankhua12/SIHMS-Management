import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private readonly route:Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        // Handle network errors or backend down scenario
        if (error.status === 0) {
          Swal.fire("Error","We're currently performing maintenance. Please check back soon!", "error");
        }
        // Handle 404 (Not Found) errors
        else if (error.status === 404) {
          Swal.fire("Error","Something Went Wrong !", "error");
        }
        // Handle 500 (Internal Server Error)
        else if (error.status === 500) {
          Swal.fire("Error","Internal Server Error. Please contact support.", "error");
        }
        // Generic error handler
        else if (error.status === 403) {
          Swal.fire({
            text: 'Your session has expired',
            icon: 'error',
            confirmButtonText: 'Ok'
          }).then((result) => {
            if (result.isConfirmed) {
              sessionStorage.clear();
              this.route.navigate(['/login']);
            }
          });
        }
        else {
          alert(`Error: ${error.message}`);
        }
        return throwError(() => error);
      })
    );
  }
}
