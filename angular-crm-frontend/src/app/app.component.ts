import { Component, OnInit } from '@angular/core';
import { MegaMenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  items: MegaMenuItem[];

  constructor() {
    this.items = [
      {
        label: 'Software',
      },
      {
        label: 'Prices',
      },
      {
        label: 'Resources',
      }
    ];
  }

  title = 'angular-crm-frontend';
}
