import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Customer } from 'src/app/models/customer';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  customers: Customer [] = [];
  newCustomer: Customer = new Customer();
  editCustomer: Customer | null = null;
  selected: Customer | null = null;

  closeResult = '';

  constructor( private modalService: NgbModal, private customerSrv: CustomerService) { }

  ngOnInit(): void {
    this.loadCustomers();
  }

  displayCustomer(customer: Customer): void {
    this.selected = customer;
  }

  displayTable(): void {
    this.selected = null;
  }

  setEditCustomer(): void {
    this.editCustomer = Object.assign({}, this.selected)
  }

  loadCustomers() {
    this.customerSrv.index().subscribe(
      custs => {
        this.customers = custs;
      },
      fail => {
        console.error('OrderListComponent.loadCustomers(): error retrieving customers');
      }
    );
  }

  createCustomer(): void {
    this.customerSrv.create(this.newCustomer).subscribe(
      data => {
        this.loadCustomers();
      },
      error =>{
        console.log(error);
        console.log("Error");
      }
    );
    this.newCustomer = new Customer();
  }

  updateCustomer(customer: Customer) {
    this.customerSrv.updateCustomer(customer).subscribe(
      data => {
        this.loadCustomers();
      },
      error =>{
        console.log(error);
        console.log("Error");
      }
    );
    this.editCustomer = null;
    this.selected = null;
  }

  deleteCustomer(id: number){
    this.customerSrv.destroy(id).subscribe(
      data => {
        this.loadCustomers();
      },
      error =>{
        console.log(error);
        console.log("Error");
      }
    );
    }

  open(addOrder: any) {
    this.modalService.open(addOrder, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    },
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
