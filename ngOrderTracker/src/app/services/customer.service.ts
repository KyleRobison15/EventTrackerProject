import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  // baseUrl = 'http://localhost:8084/';
  url = environment.baseUrl + 'api'


  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.url + '/customers', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('OrderService.index() Error retrieving orders' + err);
      })
    );
  }

  public create(customer: Customer){
    return this.http.post<Customer>(this.url + '/customers', customer, this.getHttpOptions())
    .pipe(catchError((err: any) => {
      console.log(err);
      return throwError(`Error creating customer: ${err}`);
    })
    );
  }

  public updateCustomer(customer: Customer) {
    return this.http.put<Customer>(this.url + '/customers', customer, this.getHttpOptions())
    .pipe(catchError((err: any) => {
      console.log(err);
      return throwError(`Error updating customer: ${err}`);
    })
    );
  }

  public destroy (id: number){
    return this.http.delete<Customer>(this.url + '/customers/' + id, this.getHttpOptions())
    .pipe(catchError((err: any) => {
      console.log(err);
      return throwError(`Error deleting customer: ${err}`);
    })
    );
  }

  getHttpOptions(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest',
      })
    };
    return httpOptions;
  }

}
