import { ReqProductId } from "./req-product-id";

export class ReqProduct {

  id: ReqProductId;
  unitsOrdered: number;

  constructor(id: ReqProductId = new ReqProductId(), unitsOrdered: number = 0)

  {
    this.id = id;
    this.unitsOrdered = unitsOrdered;
  }

}
