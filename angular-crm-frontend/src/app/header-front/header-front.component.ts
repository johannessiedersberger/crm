import { Component } from '@angular/core';
import { MegaMenuItem } from 'primeng/api';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header-front',
  templateUrl: './header-front.component.html',
  styleUrls: ['./header-front.component.css']
})
export class HeaderFrontComponent {
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
