export class Product {

  id: number;
  name: string;
  unitQuantity: number;
  unitPrice: number;
  imageUrl: string;

  constructor(id: number = 0, name: string = '', unitQuantity: number = 0,
              unitPrice: number = 0, imageUrl: string = '')
  {
    this.id = id;
    this.name = name;
    this.unitQuantity = unitQuantity;
    this.unitPrice = unitPrice;
    this.imageUrl = imageUrl;
  }


}
