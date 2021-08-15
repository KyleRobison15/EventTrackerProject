import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  baseUrl = 'http://localhost:8084/';
  url = this.baseUrl + 'api'


  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.url + '/customers').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('OrderService.index() Error retrieving orders' + err);
      })
    );
  }

}
