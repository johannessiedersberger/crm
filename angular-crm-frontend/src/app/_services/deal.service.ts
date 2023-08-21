import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Deal } from '../deals-page/deal';
import { Observable } from 'rxjs';

const DEAL_API = 'http://localhost:8080/api/deals';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class DealService {

  constructor(private http: HttpClient) { }

  getAllDeals(): Observable<Array<Deal>> {
    return this.http.get<Array<Deal>>(DEAL_API, httpOptions);
  }

  updateDeal(deal: Deal) {
    return this.http.put(DEAL_API + '/' + deal.dealId, deal, httpOptions);
  }

  deleteDeal(dealId: number) {
    return this.http.delete(DEAL_API + '/' + dealId, httpOptions);
  }

  createDeal(newDeal: Deal) {
    return this.http.post(DEAL_API, newDeal, httpOptions);
  }
}
