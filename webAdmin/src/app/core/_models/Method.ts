export class PaymentMethod {
  id: string;
  name: string;
  active: boolean;
  deleted: boolean;
  constructor() {
    this.active = true;
    this.deleted = false;
  }
}
export class DeliveryMethod {
  id: string;
  name: string;
  active: boolean;
  deleted: boolean;
  constructor() {
    this.active = true;
    this.deleted = false;
  }
}
