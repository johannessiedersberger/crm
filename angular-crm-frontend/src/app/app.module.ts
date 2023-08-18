import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';

import { AccordionModule } from 'primeng/accordion';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DataViewModule, DataViewLayoutOptions } from 'primeng/dataview';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { InputTextModule } from 'primeng/inputtext';

import { FormsModule } from '@angular/forms';

import { PasswordModule } from 'primeng/password';
import { MegaMenuModule } from 'primeng/megamenu';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { HeaderFrontComponent } from './header-front/header-front.component';
import { DashboardPageComponent } from './dashboard-page/dashboard-page.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { authInterceptorProviders } from './_helper/auth.interceptor';
import { ToastrModule } from 'ngx-toastr';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';
import { HeaderDashboardComponent } from './header-dashboard/header-dashboard.component';
import { ChartModule } from 'primeng/chart';
import { CustomerPageComponent } from './customer-page/customer-page.component';
import { TableModule } from 'primeng/table';

import { CommonModule } from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    SignupPageComponent,
    HomePageComponent,
    HeaderFrontComponent,
    DashboardPageComponent,
    HeaderDashboardComponent,
    CustomerPageComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AccordionModule,
    ButtonModule,
    DataViewModule,
    CardModule,
    InputTextModule,
    FormsModule,
    PasswordModule,
    MegaMenuModule,
    HttpClientModule,
    ToastModule,
    ChartModule,
    CardModule,
    TableModule,
    CommonModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent],
  schemas: [

  ]
})
export class AppModule { }
