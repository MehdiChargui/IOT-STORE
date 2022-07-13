package com.example.serviceorderread.controllers;
import com.example.serviceorderread.entites.Order;
import coreapi.querys.GetAllOrderQuery;
import coreapi.querys.GetOrderByCustomerQuery;
import coreapi.querys.GetOrderByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/order/read")
public class OrderQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allOrder")
    public List<Order> orderList() {
        List<Order> response =queryGateway.query(new GetAllOrderQuery(), ResponseTypes.multipleInstancesOf(Order.class)).join();
        return response;
    }

    @GetMapping("/getById/{id}")
    public Order getOrder(@PathVariable String id) {
        Order response =queryGateway.query(new GetOrderByIdQuery(id),ResponseTypes.instanceOf(Order.class)).join();
        return response;
    }

    @GetMapping("/getByCustomer/{customer}")
    public List<Order> getOrderByCustomer(@PathVariable String customer) {
        List<Order> response =queryGateway.query(new GetOrderByCustomerQuery(customer),ResponseTypes.multipleInstancesOf(Order.class)).join();
        return response;
    }

}
