export class Customer {

  id: number;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  street: string;
  city: string;
  stateAbbr: string;
  postalCode: string;

  constructor(id: number = 0, firstName: string = '', lastName: string = '', email: string = '',
              phone: string = '', street: string = '', city: string = '', stateAbbr: string = '', postalCode: string = '')

  {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.street = street;
    this.city = city;
    this.stateAbbr = stateAbbr;
    this.postalCode = postalCode;
  }

}
