import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Customer } from 'src/app/models/customer';
import { Order } from 'src/app/models/order';
import { Product } from 'src/app/models/product';
import { CustomerService } from 'src/app/services/customer.service';
import { OrderService } from 'src/app/services/order.service';
import { ProductService } from 'src/app/services/product.service';


@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

  orders: Order[] = [];
  customers: Customer[] = [];
  products: Product[] = [];
  newOrder: Order = new Order();
  editOrder: Order | null = null;
  showCompleted: boolean = false;
  selected: Order | null = null;
  closeResult: string = '';

  constructor(private orderSrv: OrderService,
              private datePipe: DatePipe,
              private modalService: NgbModal,
              private customerSrv: CustomerService,
              private productSrv: ProductService) { }


  ngOnInit(): void {
    this.loadOrders();
    this.loadCustomers();
    this.loadProducts();
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

  createOrder(): void {
    this.orderSrv.create(this.newOrder).subscribe(
      data => {
        this.loadOrders();
      },
      error =>{
        console.log(error);
        console.log("Error");
      }
    );
    this.newOrder = new Order();
    // this.todoService.index();  // Refresh the todo list to get latest copy.
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

  loadProducts() {
    this.productSrv.index().subscribe(
      prods => {
        this.products = prods;
      },
      fail => {
        console.error('OrderListComponent.loadProducts(): error retrieving products');
      }
    );
  }

}
