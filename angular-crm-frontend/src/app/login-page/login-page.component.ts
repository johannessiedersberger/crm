import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginRequestPayload } from './login-request.payload';
import { ToastrService } from 'ngx-toastr';
import { throwError } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css'],
  providers: [MessageService]
})
export class LoginPageComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  loginSuccessFull = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router, private messageService: MessageService) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  login(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe({
      next: data => {
        console.log(data)
        this.tokenStorage.saveToken(data.access_token);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.loginSuccessFull = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.router.navigateByUrl('dashboard');
      },
      error: err => {
        console.log(err);
        this.errorMessage = err.error;
        this.isLoginFailed = true;
        this.showError(this.errorMessage);
      }
    });
  }

  showError(err: any) {
    this.messageService.add({ severity: 'error', summary: 'Error', detail: err });
  }

}
