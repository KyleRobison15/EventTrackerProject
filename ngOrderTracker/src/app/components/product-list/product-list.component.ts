import { Component, OnInit } from '@angular/core';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  products: Product [] = [];
  newProduct: Product = new Product();
  editProduct: Product | null = null;
  selected: Product | null = null;

  closeResult = '';

  constructor( private modalService: NgbModal, private productSrv: ProductService) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  displayCustomer(product: Product): void {
    this.selected = product;
  }

  displayTable(): void {
    this.selected = null;
  }

  setEditProduct(): void {
    this.editProduct = Object.assign({}, this.selected)
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

  createProduct(): void {
    this.productSrv.create(this.newProduct).subscribe(
      data => {
        this.loadProducts();
      },
      error =>{
        console.log(error);
        console.log("Error");
      }
    );
    this.newProduct = new Product();
  }

  updateProduct(product: Product) {
    this.productSrv.updateProduct(product).subscribe(
      data => {
        this.loadProducts();
      },
      error =>{
        console.log(error);
        console.log("Error");
      }
    );
    this.editProduct = null;
    this.selected = null;
  }

  deleteProduct(id: number){
    this.productSrv.destroy(id).subscribe(
      data => {
        this.loadProducts();
      },
      error =>{
        console.log(error);
        console.log("Error");
      }
    );
    }

  open(addProd: any) {
    this.modalService.open(addProd, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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
