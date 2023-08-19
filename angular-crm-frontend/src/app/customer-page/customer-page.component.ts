import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../_services/customer.service';
import { Customer } from './customer';
import { InputTextModule } from 'primeng/inputtext';



@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.css']
})
export class CustomerPageComponent implements OnInit {
  customers: Array<Customer> = [];
  loading: boolean = true;
  visible: boolean = false;
  createCustomerVisible: boolean = false;

  selectedCustomer: Customer = {
    customerId: 0,
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: ""
  };

  newCustomer: any = {
    firstName: "",
    lastName: "",
    email: "",
    phoneNumber: ""
  };


  constructor(private customerSerice: CustomerService) { }

  ngOnInit(): void {
    this.setCustomerData();
  }

  showDialog(customer: Customer) {

    this.selectedCustomer = Object.assign({}, customer);
    this.visible = true;
  }

  confirm() {
    this.visible = false;
    this.customerSerice.updateCustomer(this.selectedCustomer).subscribe({
      next: data => {
        console.log("success");
        // load data
        this.setCustomerData();
      },
      error: data => {
        console.log(data);
      }
    });
    console.log(this.selectedCustomer);


  }

  cancel() {
    this.visible = false;
  }

  deleteCustomer(customerId: number) {
    this.customerSerice.deleteCustomer(customerId).subscribe({
      next: data => {
        console.log("success");
        this.setCustomerData();
      },
      error: data => {
        console.log("error");
      }
    })
  }

  setCustomerData() {
    this.customerSerice.getAllCustomers().subscribe({
      next: data => {
        this.customers = data;
        this.loading = false;
      }
    });
  }

  showDialogCreateCustomer() {
    this.createCustomerVisible = true;
  }

  confirmCreateCustomer() {
    this.createCustomerVisible = false;
    this.customerSerice.createCustomer(this.newCustomer).subscribe({
      next: data => {
        this.setCustomerData();
        this.newCustomer = {
          firstName: "",
          lastName: "",
          email: "",
          phoneNumber: ""
        };
      },
      error: data => {

      }
    })

  }

  cancelCreateCustomer() {
    this.createCustomerVisible = false;

  }
}
