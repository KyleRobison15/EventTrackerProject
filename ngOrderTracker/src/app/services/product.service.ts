import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  // baseUrl = 'http://localhost:8084/';
  url = environment.baseUrl + 'api'


  constructor(
    private http: HttpClient
  ) { }

  index(): Observable<Product[]> {
    return this.http.get<Product[]>(this.url + '/products').pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('OrderService.index() Error retrieving orders' + err);
      })
    );
  }

  public create(product: Product){
    return this.http.post<Product>(this.url + '/products', product, this.getHttpOptions())
    .pipe(catchError((err: any) => {
      console.log(err);
      return throwError(`Error creating product: ${err}`);
    })
    );
  }

  public updateProduct(product: Product) {
    return this.http.put<Product>(this.url + '/products', product, this.getHttpOptions())
    .pipe(catchError((err: any) => {
      console.log(err);
      return throwError(`Error updating product: ${err}`);
    })
    );
  }

  public destroy (id: number){
    return this.http.delete<Product>(this.url + '/products/' + id, this.getHttpOptions())
    .pipe(catchError((err: any) => {
      console.log(err);
      return throwError(`Error deleting product: ${err}`);
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
