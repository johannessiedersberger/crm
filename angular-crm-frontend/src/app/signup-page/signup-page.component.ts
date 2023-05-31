import { Component } from '@angular/core';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css']
})
export class SignupPageComponent {
  firstname: string;
  lastname: string;
  email: string;
  password1: string;
  password2: string;


  constructor() {
    this.firstname = "";
    this.lastname = "";
    this.email = "";
    this.password1 = "";
    this.password2 = "";
  }
}
