package com.example.serviceorderread.services;

import com.example.serviceorderread.entites.Order;
import com.example.serviceorderread.repositories.OrderRepository;
import coreapi.events.CreatedOrderEvent;
import coreapi.events.DeletedCustomerEvent;
import coreapi.events.DeletedOrderEvent;
import coreapi.events.UpdatedOrderEvent;
import coreapi.querys.GetAllOrderQuery;
import coreapi.querys.GetOrderByCustomerQuery;
import coreapi.querys.GetOrderByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class OrderServiceHandler {
    @Autowired
    private OrderRepository orderRepository;



    @EventHandler
    public void on(CreatedOrderEvent event){
        log.info("CreatedOrderEvent recived");
        Order order = new Order(
                event.getId(),
                event.getReference(),
                event.getOrderDate(),
                event.getCustomer(),
                event.getDeliveryAddress(),
                event.getDeliveryMethod(),
                event.getPaymentMethod(),
                event.getStatus(),
                event.getPaymentStatus(),
                event.getTotal(),
                event.getTotalTTC(),
                event.getDetails()
        );
        orderRepository.save(order);
    }

    @EventHandler
    public void on(UpdatedOrderEvent event) {
        log.info("UpdatedOrderEvent recived");
        Order order = orderRepository.findById(event.getId()).get();
        order.setReference(event.getReference());
        order.setCustomer(event.getCustomer());
        order.setDeliveryAddress(event.getDeliveryAddress());
        order.setDeliveryMethod(event.getDeliveryMethod());
        order.setPaymentMethod(event.getPaymentMethod());
        order.setStatus(event.getStatus());
        order.setPaymentStatus(event.getPaymentStatus());
        order.setTotal(event.getTotal());
        order.setTotalTTC(event.getTotalTTC());
        order.setDetails(event.getDetails());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(DeletedOrderEvent event) {
        log.info("DeletedOrderEvent recived");
        orderRepository.deleteById(event.getId());
    }

    @EventHandler
    public void on(DeletedCustomerEvent event) {
        log.info("DeletedCustomerEvent recived");
        orderRepository.deleteOrdersByCustomer(event.getId());
    }

    @QueryHandler
    public List<Order> on(GetAllOrderQuery query){
        return orderRepository.findAll();
    }

    @QueryHandler
    public Order on(GetOrderByIdQuery query){
        return orderRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public List<Order> on(GetOrderByCustomerQuery query){return orderRepository.findByCustomer(query.getCustomer());}

}
