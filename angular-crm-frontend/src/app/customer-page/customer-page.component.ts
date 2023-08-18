import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../_services/customer.service';
import { Customer } from './customer';


@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.css']
})
export class CustomerPageComponent implements OnInit {
  customers: Array<Customer> = [];

  constructor(private customerSerice: CustomerService) { }

  ngOnInit(): void {
    this.customerSerice.getAllCustomers().subscribe({
      next: data => {
        this.customers = data;
      }
    });
  }
}
