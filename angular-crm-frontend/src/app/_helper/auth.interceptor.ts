import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpResponse, HttpEvent, HttpErrorResponse, HttpHandler, HTTP_INTERCEPTORS } from "@angular/common/http";
import { Observable, BehaviorSubject, throwError } from "rxjs";
import { AuthService } from "../_services/auth.service";
import { catchError, switchMap, take, filter } from "rxjs/operators";
import { LoginResponse } from "../login-page/login-response.payload";
import { TokenStorageService } from "../_services/token-storage.service";

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable({
    providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {
    constructor(private token: TokenStorageService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let authReq = req;
        const token = this.token.getToken();
        if (token != null) {

            authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });

        }
        return next.handle(authReq);
    }

}

export const authInterceptorProviders = [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];