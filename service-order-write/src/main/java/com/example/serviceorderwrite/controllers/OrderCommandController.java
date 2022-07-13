package com.example.serviceorderwrite.controllers;

import coreapi.commands.CreateOrderCommand;
import coreapi.commands.DeleteOrderCommand;
import coreapi.commands.UpdateOrderCommand;
import coreapi.details.Details;
import coreapi.dtos.CreateOrderRequestDTO;
import coreapi.dtos.UpdateOrderRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/order/write")
public class OrderCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createOrder(@RequestBody CreateOrderRequestDTO request) {
        if(Objects.isNull(request))
            return "Order can not be empty";
        try{
            CreateOrderCommand createOrderCommand = new CreateOrderCommand(
                    UUID.randomUUID().toString(),
                    request.getReference(),
                    Optional.ofNullable(request.getOrderDate()).orElse(new Date()),
                    request.getCustomer(),
                    request.getDeliveryAddress(),
                    request.getDeliveryMethod(),
                    request.getPaymentMethod(),
                    request.getStatus(),
                    false,
                    Optional.ofNullable(request.getTotal()).orElse(0.0),
                    Optional.ofNullable(request.getTotalTTC()).orElse(0.0),
                    mapIDsToDetails(request.getDetails())
            );
            commandGateway.send(createOrderCommand);
            return createOrderCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the order.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateOrder(@RequestBody UpdateOrderRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Order can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateOrderCommand updateOrderCommand = new UpdateOrderCommand(
                        request.getId(),
                        request.getReference(),
                        request.getCustomer(),
                        request.getDeliveryAddress(),
                        request.getDeliveryMethod(),
                        request.getPaymentMethod(),
                        request.getStatus(),
                        request.getPaymentStatus(),
                        request.getTotal(),
                        request.getTotalTTC(),
                        mapIDsToDetails(request.getDetails())
                );
                commandGateway.send(updateOrderCommand);
                return updateOrderCommand;
            }else
                return "Order not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating order with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public Object deleteCategory(@PathVariable String id) {
        if(id.isEmpty())
            return "ID order can not be empty";
        try {
            DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(id);
            commandGateway.send(deleteOrderCommand);
            return deleteOrderCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete order with id " + id;
        }
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity=new ResponseEntity<String>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    private List<Details> mapIDsToDetails(List<Details> details) {
        if (Objects.isNull(details))
            return null;
        else {
        return details.stream().map(sc -> new Details(
                Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                sc.getArticle(),
                sc.getQuantity(),
                sc.getUnitPrice()
        )).collect(Collectors.toList());
        }
    }
}
