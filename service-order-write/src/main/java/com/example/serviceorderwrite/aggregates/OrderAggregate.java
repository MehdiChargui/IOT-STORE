package com.example.serviceorderwrite.aggregates;

import coreapi.commands.CreateOrderCommand;
import coreapi.commands.DeleteOrderCommand;
import coreapi.commands.UpdateOrderCommand;
import coreapi.details.Details;
import coreapi.events.CreatedOrderEvent;
import coreapi.events.DeletedOrderEvent;
import coreapi.events.UpdatedOrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;
import java.util.List;

@Slf4j
@Aggregate
public class OrderAggregate {
    @AggregateIdentifier

    private String idOrder;
    private String reference;
    private Date orderDate;
    private String customer;
    private String deliveryAddress;
    private String deliveryMethod;
    private String paymentMethod;
    private String status;
    private Boolean paymentStatus;
    private double total;
    private  double totalTTC;
    private List<Details> details;

    public OrderAggregate(){}

    //Function de decision
    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        log.info("CreateOrderCommand received");
        if(createOrderCommand.getReference().isEmpty()) throw new RuntimeException("Reference order can not be empty");
        AggregateLifecycle.apply(new CreatedOrderEvent(
                createOrderCommand.getId(),
                createOrderCommand.getReference(),
                createOrderCommand.getOrderDate(),
                createOrderCommand.getCustomer(),
                createOrderCommand.getDeliveryAddress(),
                createOrderCommand.getDeliveryMethod(),
                createOrderCommand.getPaymentMethod(),
                createOrderCommand.getStatus(),
                createOrderCommand.getPaymentStatus(),
                createOrderCommand.getTotal(),
                createOrderCommand.getTotalTTC(),
                createOrderCommand.getDetails()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedOrderEvent event) {
        log.info("CreatedOrderEvent occured");
        this.idOrder= event.getId();
        this.reference=event.getReference();
        this.orderDate=event.getOrderDate();
        this.customer=event.getCustomer();
        this.deliveryAddress=event.getDeliveryAddress();
        this.deliveryMethod= event.getDeliveryMethod();
        this.paymentMethod=event.getPaymentMethod();
        this.status= event.getStatus();
        this.paymentStatus=event.getPaymentStatus();
        this.total= event.getTotal();
        this.totalTTC= event.getTotalTTC();
        this.details=event.getDetails();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateOrderCommand updateOrderCommand) {
        log.info("UpdateOrderCommand received");
        if(updateOrderCommand.getReference().isEmpty()) throw new RuntimeException("Reference Order can not be empty");
        AggregateLifecycle.apply(new UpdatedOrderEvent(
                updateOrderCommand.getId(),
                updateOrderCommand.getReference(),
                updateOrderCommand.getCustomer(),
                updateOrderCommand.getDeliveryAddress(),
                updateOrderCommand.getDeliveryMethod(),
                updateOrderCommand.getPaymentMethod(),
                updateOrderCommand.getStatus(),
                updateOrderCommand.getPaymentStatus(),
                updateOrderCommand.getTotal(),
                updateOrderCommand.getTotalTTC(),
                updateOrderCommand.getDetails()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedOrderEvent event) {
        log.info("UpdatedOrderEvent occured");
        this.idOrder= event.getId();
        this.reference= event.getReference();
        this.customer= event.getCustomer();
        this.deliveryAddress= event.getDeliveryAddress();
        this.deliveryMethod= event.getDeliveryMethod();
        this.paymentMethod= event.getPaymentMethod();
        this.status= event.getStatus();
        this.paymentStatus=event.getPaymentStatus();
        this.total= event.getTotal();
        this.totalTTC= event.getTotalTTC();
        this.details=event.getDetails();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteOrderCommand deleteOrderCommand) {
        log.info("DeleteOrderCommand received");
        if(deleteOrderCommand.getId().isEmpty()) throw new RuntimeException("Id banner can not be empty");
        AggregateLifecycle.apply(new DeletedOrderEvent(
                deleteOrderCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedOrderEvent event) {
        log.info("DeletedOrderEvent occured");
        this.idOrder=event.getId();
    }
}
