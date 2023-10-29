import { EventEmitter, Output, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';
import { LoginRequestPayload } from '../login-page/login-request.payload';
import { map, tap } from 'rxjs/operators';
import { LoginResponse } from '../login-page/login-response.payload';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }), 
  responseType: 'text' as 'text'
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'authenticate', {
      email,
      password
    }, httpOptions);
  }

  register(email: string, password: string, password2: string): Observable<any> {
    console.log({
      email,
      password,
      password2
    });
    return this.http.post(AUTH_API + 'signup', {
      email,
      password,
      password2
    }, httpOptions);
  }
}
