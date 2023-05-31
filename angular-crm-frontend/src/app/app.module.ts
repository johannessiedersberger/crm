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

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    SignupPageComponent,
    HomePageComponent,
    HeaderFrontComponent,
    DashboardPageComponent
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
    MegaMenuModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [

  ]
})
export class AppModule { }
