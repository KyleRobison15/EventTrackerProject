import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Order } from '../models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  baseUrl = 'http://localhost:8084/';
  url = this.baseUrl + 'api'


  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Order[]> {
    return this.http.get<Order[]>(this.url + '/reqs').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('OrderService.index() Error retrieving orders' + err);
      })
    );
  }

}
