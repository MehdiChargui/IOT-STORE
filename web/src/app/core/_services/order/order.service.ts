import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ApiService } from 'src/app/core';
import { Order } from 'src/app/core/_models/Order';
import { PaymentMethod, DeliveryMethod } from 'src/app/core/_models/Method';

const routes = {
  getAllOrders: () => `/allOrder`,
  orderWithId: (id: string) => `/getById/${id}`,
  addOrder:() => `/create`,
  orderStatus: (id: string) => `/orderStatus/${id}`,
  orderByCustomer: (id: string) => `/getByCustomer/${id}`,
  
  paymentMethod: () => `/allPaymentMethods`,
  paymentMethodWithId: (id: string) => `/getById/${id}`,
  createPaymentMethod:()=>`/create`,
  updatePaymentMethod:(id: string) =>`/update/${id}`, 
  deletePaymentMethod:(id:string) =>`/delete/${id}`,

  deliveryMethod: () => `/allDeliveryMethods`,
  deliverytMethodWithId: (id: string) => `/getById/${id}`,
  createDeliveryMethod:()=>`/create`,
  updateDeliveryMethod:(id: string) =>`/update/${id}`, 
  deleteDeliveryMethod:(id:string) =>`/delete/${id}`,
};
@Injectable({
  providedIn: 'root'
})
export class OrderService {
  route_order_write='/MS-ORDER-WRITE/order/write';
  route_order_read='/MS-ORDER-READ/order/read';
  route_mpayment_write='/MS-PAYMENTMETHOD-WRITE/paymentMethod/write';
  route_mpayment_read='/MS-PAYMENTMETHOD-READ/paymentMethod/read';
  route_mdelivery_write='/MS-DELIVERYMETHOD-WRITE/deliveryMethod/write';
  route_mdelivery_read='/MS-DELIVERYMETHOD-READ/deliveryMethod/read';
  constructor(private api: ApiService) {}

  getAllOrders(): Observable<Order[]> {
    return this.api.get<Order[]>(this.route_order_read + routes.getAllOrders(), Order);
  }
  getOrder(id: string): Observable<Order> {
    return this.api.get<Order>(this.route_order_read + routes.orderWithId(id), Order);
  }

  addOrder(order:Order): Observable<Order> {
    return this.api.post<Order>(this.route_order_write + routes.addOrder(),order,Order);
  }
  getCustomerOrders(id:string): Observable<Order[]> {
    return this.api.get<Order[]>(this.route_order_read + routes.orderByCustomer(id), Order);
  }

  
  getAllPaymentMethods(): Observable<PaymentMethod[]> {
    return this.api.get<PaymentMethod[]>(
      this.route_mpayment_read + routes.paymentMethod(),
      PaymentMethod
    );
  }
  getPaymentMethod(id: string): Observable<PaymentMethod> {
    return this.api.get<PaymentMethod>(
      this.route_mpayment_read + routes.paymentMethodWithId(id),
      PaymentMethod
    );
  }
  addPaymentMethod(paymentMethod: PaymentMethod): Observable<PaymentMethod> {
    return this.api.post<PaymentMethod>(
      this.route_mpayment_write + routes.createPaymentMethod(),
      paymentMethod,
      PaymentMethod
    );
  }
  updatePaymentMethod(paymentMethod: PaymentMethod): Observable<PaymentMethod> {
    return this.api.put<PaymentMethod>(
      this.route_mpayment_write + routes.updatePaymentMethod(paymentMethod.id),
      paymentMethod,
      PaymentMethod
    );
  }
  deletePaymentMethod(id: string): Observable<PaymentMethod> {
    return this.api.delete<PaymentMethod>(
      this.route_mpayment_write + routes.deletePaymentMethod(id),
      PaymentMethod
    );
  }

  getAllDeliveryMethods(): Observable<DeliveryMethod[]> {
    return this.api.get<DeliveryMethod[]>(
      this.route_mdelivery_read + routes.deliveryMethod(),
      DeliveryMethod
    );
  }
  getDeliveryMethod(id: string): Observable<DeliveryMethod> {
    return this.api.get<DeliveryMethod>(
      this.route_mdelivery_read + routes.deliverytMethodWithId(id),
      DeliveryMethod
    );
  }
  addDeliveryMethod(
    deliveryMethod: DeliveryMethod
  ): Observable<DeliveryMethod> {
    return this.api.post<DeliveryMethod>(
      this.route_mdelivery_write + routes.createDeliveryMethod(),
      deliveryMethod,
      DeliveryMethod
    );
  }
  updateDeliveryMethod(
    deliveryMethod: DeliveryMethod
  ): Observable<DeliveryMethod> {
    return this.api.put<DeliveryMethod>(
      this.route_mdelivery_write + routes.updateDeliveryMethod(deliveryMethod.id),
      deliveryMethod,
      DeliveryMethod
    );
  }
  deleteDeliveryMethod(id: string): Observable<DeliveryMethod> {
    return this.api.delete<DeliveryMethod>(
      this.route_mdelivery_write + routes.deleteDeliveryMethod(id),
      DeliveryMethod
    );
  }
}
