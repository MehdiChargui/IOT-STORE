package com.example.serviceadminwrite.controllers;

import coreapi.commands.CreateAdminCommand;
import coreapi.commands.DeleteAdminCommand;
import coreapi.commands.UpdateAdminCommand;
import coreapi.dtos.CreateAdminRequestDTO;
import coreapi.dtos.UpdateAdminRequestDTO;
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
@RequestMapping(path = "/admin/write")
public class AdminCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createAdmin(@RequestBody CreateAdminRequestDTO request) {
        if(Objects.isNull(request))
            return "Admin can not be empty";
        try {
            CreateAdminCommand createAdminCommand = new CreateAdminCommand(
                UUID.randomUUID().toString(),
                request.getFirstname(),
                request.getLastname(),
                request.getAddress(),
                request.getPostalCode(),
                request.getPhoneNumber(),
                request.getFunction(),
                request.getMail(),
                request.getLogin(),
                request.getPassword(),
                request.getRole(),
                false
                );
            //CompletableFuture<String> commandResponse = commandGateway.send(createAdminCommand);
            commandGateway.send(createAdminCommand);
            return createAdminCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the admin.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateAdmin(@RequestBody UpdateAdminRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Admin can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateAdminCommand updateAdminCommand = new UpdateAdminCommand(
                        request.getId(),
                        request.getFirstname(),
                        request.getLastname(),
                        request.getAddress(),
                        request.getPostalCode(),
                        request.getPhoneNumber(),
                        request.getFunction(),
                        request.getMail(),
                        request.getLogin(),
                        request.getPassword(),
                        request.getRole(),
                        request.getDeleted()
                );
                commandGateway.send(updateAdminCommand);
                return updateAdminCommand;
            }else
                return "Admin not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating Admin with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{idAdmin}")
    public Object deleteAdmin(@PathVariable String idAdmin) {
        if(idAdmin.isEmpty())
            return "ID Admin can not be empty";
        try {
            DeleteAdminCommand deleteAdminCommand = new DeleteAdminCommand(idAdmin);
            commandGateway.send(deleteAdminCommand);
            return "admin deleted successfully!";
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete admin with id " + idAdmin;
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