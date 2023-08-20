import { Component, OnInit } from '@angular/core';
import { Deal } from './deal';

@Component({
  selector: 'app-deals-page',
  templateUrl: './deals-page.component.html',
  styleUrls: ['./deals-page.component.css']
})
export class DealsPageComponent implements OnInit {
  deals: any;
  draggedDeal: any;
  draggedDealSource: any;

  ngOnInit() {
    this.deals = [[
      { id: 0, name: 'IT-Consulting Contract' },
      { id: 1, name: 'App-Development' }
    ], [], [], []]
  }

  dragStart(deal: Deal, source: number) {
    this.draggedDeal = deal;
    this.draggedDealSource = source;

    console.log("Drag started Deal: " + deal.id + " Source:" + this.draggedDealSource);
  }

  drop(destination: number) {
    this.deals[destination] = [...(this.deals[destination] as Deal[]), this.draggedDeal];
    this.deals[this.draggedDealSource] = this.deals[this.draggedDealSource]?.filter((p: Deal) => p.id != this.draggedDeal.id);
    this.draggedDeal = null;
    this.draggedDealSource = null;

  }

  dragEnd() {
    this.draggedDeal = null;
    this.draggedDealSource = null;
  }
}
