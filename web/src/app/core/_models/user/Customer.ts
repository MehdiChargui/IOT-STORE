export class Customer {
  id: string;
  firstname: string;
  lastname: string;
  phoneNumber: string;
  addresses: Address[]=[];
  function: string;
  registrationDate: Date;
  mail: string;
  login: string;
  password: string;
  active: Boolean;
  accountType:string;
  mailVerified: Boolean;
  deleted: Boolean;
}
export class Address {
  id:string;
  address: string;
  postalCode: string;
  city: string;
}
