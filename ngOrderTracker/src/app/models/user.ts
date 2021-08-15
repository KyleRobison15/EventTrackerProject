export class User {

  id: number;
  firstName: string;
  lastName: string;
  email: string;
  username: string;
  password: string;
  businessName: string;
  enabled: boolean;
  role: string;

  constructor(
    id: number = 0,
    firstName: string = '',
    lastName: string = '',
    email: string = '',
    username: string = '',
    password: string = '',
    businessName: string = '',
    enabled: boolean = true,
    role: string = ''
  )

  {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.username = username;
    this.password = password;
    this.businessName = businessName;
    this.enabled = enabled;
    this.role = role;
  }

}
