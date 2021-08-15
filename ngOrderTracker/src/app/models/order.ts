import { Customer } from "./customer";
import { Product } from "./product";
import { ReqProduct } from "./req-product";
import { User } from "./user";

export class Order {

  id: number;
  datePlaced: string | null;
  dueDate: string | null;
  completed: boolean;
  customer: Customer;
  reqProducts: ReqProduct [];
  products: Product [];
  user: User;

  constructor(id: number = 0, datePlaced: string = '', dueDate: string = '', completed: boolean = false,
              customer: Customer = new Customer(), reqProducts: ReqProduct[] = [], products: Product[] = [], user: User = new User())
  {
    this.id =  id;
    this.datePlaced = datePlaced;
    this.dueDate = dueDate;
    this.completed = completed;
    this.customer = customer;
    this.reqProducts = reqProducts;
    this.products = products;
    this.user = user;
  }

}
