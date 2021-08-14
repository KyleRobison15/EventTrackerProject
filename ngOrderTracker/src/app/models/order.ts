export class Order {

  id: number;
  datePlaced: string;
  dueDate: string;

  constructor(id: number = 0, datePlaced: string = '', dueDate: string = '')
  {
    this.id =  id;
    this.datePlaced = datePlaced;
    this.dueDate = dueDate;
  }

}
