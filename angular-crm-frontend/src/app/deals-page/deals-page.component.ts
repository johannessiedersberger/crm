import { Component, OnInit } from '@angular/core';
import { Deal } from './deal';
import { DealService } from '../_services/deal.service';
import { DealStages } from './dealStages';
import { Customer } from '../customer-page/customer';
import { CustomerService } from '../_services/customer.service';

@Component({
  selector: 'app-deals-page',
  templateUrl: './deals-page.component.html',
  styleUrls: ['./deals-page.component.css']
})
export class DealsPageComponent implements OnInit {
  deals: Array<Array<Deal>> = [[], [], [], []];
  draggedDeal: any;
  draggedDealSource: any;
  newDeal: any = {
    customerId: 0,
    name: "",
    amount: 0,
    dealStage: DealStages.APPOINTMENT
  }
  selectedDeal: Deal = {
    dealId: 0,
    customerId: 0,
    name: "",
    amount: 0,
    dealStage: DealStages.APPOINTMENT
  }

  customers: Array<Customer> = [];
  selectedCustomer: any;

  createDealVisible: boolean = false;
  editDealVisible: boolean = false;


  constructor(private dealSevice: DealService, private customerService: CustomerService) { }

  ngOnInit() {
    this.setDeals();
    this.setCustomers();
  }

  setCustomers() {
    this.customerService.getAllCustomers().subscribe({
      next: data => {
        this.customers = data;
      }
    })
  }

  setDeals() {
    this.dealSevice.getAllDeals().subscribe({
      next: data => {
        this.deals[0] = data.filter((d: Deal) => d.dealStage == DealStages.APPOINTMENT);
        this.deals[1] = data.filter((d: Deal) => d.dealStage == DealStages.QUALIFIEDTOBUY);
        this.deals[2] = data.filter((d: Deal) => d.dealStage == DealStages.CLOSED);
        this.deals[3] = data.filter((d: Deal) => d.dealStage == DealStages.CLOSEDANDLOST);
      }
    });
  }

  dragStart(deal: Deal, source: number) {
    this.draggedDeal = deal;
    this.draggedDealSource = source;

    console.log("Drag started Deal: " + deal.dealId + " Source:" + this.draggedDealSource);
  }

  drop(destination: number) {
    // Add deal to destination
    this.deals[destination] = [...(this.deals[destination] as Deal[]), this.draggedDeal];
    // Delete Deal from Source
    this.deals[this.draggedDealSource] = this.deals[this.draggedDealSource]?.filter((p: Deal) => p.dealId != this.draggedDeal.dealId);
    // Update Deal Stage
    this.updateDealStage(this.draggedDeal, destination);
    // Reset var storage to null
    this.draggedDeal = null;
    this.draggedDealSource = null;

  }

  updateDealStage(deal: Deal, destination: number) {
    let newDeal = deal;
    newDeal.dealStage = this.getDealStageFromNumber(destination);
    this.dealSevice.updateDeal(newDeal).subscribe({
      next: data => {
        console.log("success");
      },
      error: err => {
        console.log(err);
      }
    });
  }

  getDealStageFromNumber(stage: number): DealStages {
    if (stage == 0) {
      return DealStages.APPOINTMENT;
    }
    else if (stage == 1) {
      return DealStages.QUALIFIEDTOBUY;
    } else if (stage == 2) {
      return DealStages.CLOSED;
    } else
      return DealStages.CLOSEDANDLOST;
  }

  dragEnd() {
    this.draggedDeal = null;
    this.draggedDealSource = null;
  }

  showDialogCreateDeal() {
    this.createDealVisible = true;
  }

  confirmCreateDeal() {
    this.newDeal.customerId = this.selectedCustomer.customerId;
    this.dealSevice.createDeal(this.newDeal).subscribe({
      next: data => {
        console.log("success");
        this.setDeals();
      }
    });
    this.createDealVisible = false;

  }

  cancelCreateDeal() {
    this.createDealVisible = false;
  }

  confirmEditDeal() {
    this.dealSevice.updateDeal(this.selectedDeal).subscribe({
      next: data => {
        console.log("success");
        this.setDeals();
      }
    });
    this.editDealVisible = false;
  }

  cancelEditDeal() {
    this.editDealVisible = false;
  }

  showEditDialog(deal: Deal) {
    this.editDealVisible = true;
    this.selectedDeal = Object.assign({}, deal);
  }

  deleteDeal() {
    this.dealSevice.deleteDeal(this.selectedDeal.dealId).subscribe({
      next: data => {
        console.log("success");
        this.setDeals();
      }
    });
    this.editDealVisible = false;
  }
}
