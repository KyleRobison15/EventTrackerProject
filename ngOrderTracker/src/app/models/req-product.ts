import { ReqProductId } from "./req-product-id";

export class ReqProduct {

  id: {
    reqId: number;
    prodId: number;
  };
  unitsOrdered: number;

  constructor(reqId: number = 0, prodId: number = 0, unitsOrdered: number = 0)

  {
    this.id = {reqId, prodId};
    this.unitsOrdered = unitsOrdered;
  }

}
