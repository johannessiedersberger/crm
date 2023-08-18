import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Customer } from '../customer-page/customer';
import { Observable } from 'rxjs';

const CUSTOMER_API = 'http://localhost:8080/api/customers';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  getAllCustomers(): Observable<Array<Customer>> {
    return this.http.get<Array<Customer>>(CUSTOMER_API, httpOptions);
  }


}
