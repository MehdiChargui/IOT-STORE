package com.example.servicedeliverymethodewrite.controllers;

import coreapi.commands.CreateDeliveryMethodCommand;
import coreapi.commands.DeleteDeliveryMethodCommand;
import coreapi.commands.UpdateDeliveryMethodCommand;
import coreapi.dtos.CreateDeliveryMethodRequestDTO;
import coreapi.dtos.UpdateDeliveryMethodRequestDTO;
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
@RequestMapping(path = "/deliveryMethod/write")
public class DeliveryMethodCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createDeliveryMethod(@RequestBody CreateDeliveryMethodRequestDTO request) {
        if(Objects.isNull(request))
            return "Delivery method can not be empty";
        try{
            CreateDeliveryMethodCommand createDeliveryMethodCommand = new CreateDeliveryMethodCommand(
                    UUID.randomUUID().toString(),
                    request.getName(),
                    true,
                    false
            );
            commandGateway.send(createDeliveryMethodCommand);
            return createDeliveryMethodCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the delivery method.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateDeliveryMethod(@RequestBody UpdateDeliveryMethodRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Delivery method can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateDeliveryMethodCommand updateDeliveryMethodCommand = new UpdateDeliveryMethodCommand(
                        request.getId(),
                        request.getName(),
                        request.getActive(),
                        request.getDeleted()
                );
                commandGateway.send(updateDeliveryMethodCommand);
                return updateDeliveryMethodCommand;
            }else
                return "Delivery method not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating delivery method with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public Object deleteDeliveryMethod(@PathVariable String id) {
        if(id.isEmpty())
            return "ID delivery method can not be empty";
        try {
            DeleteDeliveryMethodCommand deleteDeliveryMethodCommand = new DeleteDeliveryMethodCommand(id);
            commandGateway.send(deleteDeliveryMethodCommand);
            return deleteDeliveryMethodCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete delivery method with id " + id;
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