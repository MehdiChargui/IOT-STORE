package com.example.servicepaymentmethodewrite.controllers;

import coreapi.commands.CreatePaymentMethodCommand;
import coreapi.commands.DeletePaymentMethodCommand;
import coreapi.commands.UpdatePaymentMethodCommand;
import coreapi.dtos.CreatePaymentMethodRequestDTO;
import coreapi.dtos.UpdatePaymentMethodRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping(path = "/paymentMethod/write")
public class PaymentMethodCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createPaymentMethod(@RequestBody CreatePaymentMethodRequestDTO request) {
        if(Objects.isNull(request))
            return "Payment method can not be empty";
        try{
            CreatePaymentMethodCommand createPaymentMethodCommand = new CreatePaymentMethodCommand(
                UUID.randomUUID().toString(),
                request.getName(),
                true,
                false
            );
            commandGateway.send(createPaymentMethodCommand);
            return createPaymentMethodCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the payment method.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updatePaymentMethod(@RequestBody UpdatePaymentMethodRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Payment method can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdatePaymentMethodCommand updatePaymentMethodCommand = new UpdatePaymentMethodCommand(
                    request.getId(),
                    request.getName(),
                    request.getActive(),
                    request.getDeleted()
                );
                commandGateway.send(updatePaymentMethodCommand);
                return updatePaymentMethodCommand;
            }else
                return "Payment method not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating payment method with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public Object deletePaymentMethod(@PathVariable String id) {
        if(id.isEmpty())
            return "ID payment method can not be empty";
        try {
            DeletePaymentMethodCommand deletePaymentMethodCommand = new DeletePaymentMethodCommand(id);
            commandGateway.send(deletePaymentMethodCommand);
            return deletePaymentMethodCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete payment method with id " + id;
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity=new ResponseEntity<String>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}