import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];

  constructor(private orderSrv: OrderService) { }


  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders() {
    this.orderSrv.index().subscribe(
      orders => {
        this.orders = orders;
      },
      fail => {
        console.error('OrderListComponent.loadOrders(): error retrieving orders');

      }
    );
  }

}
