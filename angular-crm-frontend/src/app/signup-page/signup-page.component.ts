import { Component } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { MessageService } from 'primeng/api';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-signup-page',
  templateUrl: './signup-page.component.html',
  styleUrls: ['./signup-page.component.css'],
  providers: [MessageService]
})
export class SignupPageComponent {
  firstname: string;
  lastname: string;
  email: string;
  password1: string;
  password2: string;


  constructor(private authService: AuthService, private router: Router, private messageService: MessageService) {
    this.firstname = "";
    this.lastname = "";
    this.email = "";
    this.password1 = "";
    this.password2 = "";
  }


  signup(): void {
    this.authService.register(this.email, this.password1, this.password2).subscribe({
      next: data => {
        console.log(data)
        this.router.navigateByUrl('login');
      },
      error: err => {
        console.log(err);
        this.showError("Error Signup " + err);
      }
    });
  }

  showError(err: any) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: err });
  }
}
