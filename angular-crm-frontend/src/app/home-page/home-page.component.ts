import { Component } from '@angular/core';
import { MegaMenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent {
  items: MegaMenuItem[];


  constructor(private router: Router) {
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
}
