import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';


@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];
  newOrder: Order = new Order();
  editOrder: Order | null = null;
  showCompleted: boolean = false;
  selected: Order | null = null;
  closeResult: string = '';

  constructor(private orderSrv: OrderService,
              private datePipe: DatePipe,
              private modalService: NgbModal) { }


  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders() {
    this.orderSrv.index().subscribe(
      orders => {
        this.orders = orders;

        for (let order of orders){
            order.dueDate = this.datePipe.transform(order.dueDate, 'mediumDate');
            order.datePlaced = this.datePipe.transform(order.datePlaced, 'mediumDate');
        }

      },
      fail => {
        console.error('OrderListComponent.loadOrders(): error retrieving orders');

      }
    );
  }

  open(addOrder: any) {
    this.modalService.open(addOrder, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
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
