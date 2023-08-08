import { Component } from '@angular/core';
import { MegaMenuItem } from 'primeng/api';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-header-dashboard',
  templateUrl: './header-dashboard.component.html',
  styleUrls: ['./header-dashboard.component.css']
})
export class HeaderDashboardComponent {
  items: MegaMenuItem[];


  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router,) {
    this.items = [
      {
        label: 'Customers',
      },
      {
        label: 'Deals',
      }
    ];
  }

  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('');
  }
}
