package com.example.serviceproviderwrite.controllers;

import coreapi.commands.CreateProviderCommand;
import coreapi.commands.DeleteProviderCommand;
import coreapi.commands.UpdateProviderCommand;
import coreapi.dtos.CreateProviderRequestDTO;
import coreapi.dtos.UpdateProviderRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@RestController
@Slf4j
@RequestMapping(path = "/provider/write")
public class ProviderCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createProvider(@RequestBody CreateProviderRequestDTO request) {
        if(Objects.isNull(request))
            return "Provider can not be empty";
        try{
            CreateProviderCommand createProviderCommand = new CreateProviderCommand(
                    UUID.randomUUID().toString(),
                    request.getDesignation(),
                    request.getDescription(),
                    request.getPhoneNumber(),
                    request.getMail(),
                    request.getLink(),
                    request.getAddress(),
                    request.getPostalCode(),
                    request.getCity(),
                    request.getCountry(),
                    new Date(),
                    true,
                    false
            );
            commandGateway.send(createProviderCommand);
            return createProviderCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the provider.";
        }

    }

    @PutMapping(path = "/update/{id}")
    public Object updateProvider(@RequestBody UpdateProviderRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Provider can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateProviderCommand updateProviderCommand = new UpdateProviderCommand(
                        request.getId(),
                        request.getDesignation(),
                        request.getDescription(),
                        request.getPhoneNumber(),
                        request.getMail(),
                        request.getLink(),
                        request.getAddress(),
                        request.getPostalCode(),
                        request.getCity(),
                        request.getCountry(),
                        request.getActive(),
                        request.getDeleted()
                );
                commandGateway.send(updateProviderCommand);
                return updateProviderCommand;
            }else
                return "Provider not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating Provider with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{idProvider}")
    public Object deleteProvider(@PathVariable String idProvider) {
        if(idProvider.isEmpty())
            return "ID Provider can not be empty";
        try {
            DeleteProviderCommand deleteProviderCommand = new DeleteProviderCommand(idProvider);
            commandGateway.send(deleteProviderCommand);
            return deleteProviderCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete provider with id " + idProvider;
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