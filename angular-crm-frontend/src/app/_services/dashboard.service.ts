import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const DASHBOARD_API = 'http://localhost:8080/api/dashboard/revenue';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'text' }), 
};

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  

  constructor(private http: HttpClient) { }

  async getRevenueByQuarter(year: number, quarter: number): Promise<Observable<any>> {
    return this.http.get<any>(DASHBOARD_API + '/' + year + '/' + quarter).toPromise();
  }
}
