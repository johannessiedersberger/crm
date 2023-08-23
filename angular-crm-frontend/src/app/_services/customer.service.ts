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

  getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(CUSTOMER_API + '/' + id, httpOptions);
  }

  updateCustomer(customer: Customer) {
    return this.http.put(CUSTOMER_API + '/' + customer.customerId, customer, httpOptions);
  }

  deleteCustomer(customerId: number) {
    return this.http.delete(CUSTOMER_API + '/' + customerId, httpOptions);
  }

  createCustomer(newCustomer: Customer) {
    return this.http.post(CUSTOMER_API, newCustomer, httpOptions);
  }


}
