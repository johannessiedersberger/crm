import { Component, OnInit } from '@angular/core';
import { Deal } from './deal';
import { DealService } from '../_services/deal.service';
import { DealStages } from './dealStages';
import { Customer } from '../customer-page/customer';
import { CustomerService } from '../_services/customer.service';
import { lastValueFrom } from 'rxjs';

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
  filteredCustomers: any;

  createDealVisible: boolean = false;
  editDealVisible: boolean = false;


  constructor(private dealSevice: DealService, private customerService: CustomerService) { }

  ngOnInit() {
    this.setDeals();
    this.setCustomers();
  }

  // load and set the customers
  setCustomers() {
    this.customerService.getAllCustomers().subscribe({
      next: data => {
        this.customers = data;
      }
    })
  }

  // load and set the deals
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

  // Start dragging deal
  dragStart(deal: Deal, source: number) {
    this.draggedDeal = deal;
    this.draggedDealSource = source;

    console.log("Drag started Deal: " + deal.dealId + " Source:" + this.draggedDealSource);
  }


  // Drop deal at destination
  drop(destination: number) {
    if (destination == this.draggedDealSource) return;
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

  // update dealstage of deal
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

  // Get the Dealstage from a number
  getDealStageFromNumber(stage: number): DealStages {
    if (stage == 0) {
      return DealStages.APPOINTMENT;
    } else if (stage == 1) {
      return DealStages.QUALIFIEDTOBUY;
    } else if (stage == 2) {
      return DealStages.CLOSED;
    } else
      return DealStages.CLOSEDANDLOST;
  }

  // End the drag of the deal
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
    this.selectedDeal.customerId = this.selectedCustomer.customerId;
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

  async showEditDialog(deal: Deal) {
    this.selectedDeal = Object.assign({}, deal);
    this.selectedCustomer = this.getCustomerFromId(this.selectedDeal.customerId);
    this.editDealVisible = true;
  }

  getCustomerFromId(customerId: number): Customer {
    const customer = this.customers.filter((customer: Customer) => customer.customerId == customerId);
    return customer[0];
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

  filteredCustomer(event: any) {
    //in a real application, make a request to a remote url with the query and return filtered results, for demo we filter at client side
    let filtered: any[] = [];
    let query = event.query;
    for (let i = 0; i < this.customers.length; i++) {
      let customer = this.customers[i];
      if (customer.lastName.toLowerCase().indexOf(query.toLowerCase()) == 0) {
        filtered.push(customer);
      }
    }

    this.filteredCustomers = filtered;
  }
}
