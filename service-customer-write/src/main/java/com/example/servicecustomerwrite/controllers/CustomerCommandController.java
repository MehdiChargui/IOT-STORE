package com.example.servicecustomerwrite.controllers;


import coreapi.commands.*;
import coreapi.dtos.CreateCustomerRequestDTO;
import coreapi.dtos.CreateCustomerSignUpRequestDTO;
import coreapi.dtos.CreateTokenRequestDTO;
import coreapi.dtos.UpdateCustomerRequestDTO;
import coreapi.models.Address;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/customer/write")
public class CustomerCommandController {
    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe


    @Autowired
    private CommandGateway commandGateway;


    @PostMapping(path = "/createtoken")
    public Object createToken(@RequestBody CreateTokenRequestDTO request) {
        if(Objects.isNull(request))
            return "token can not be empty";
        try{
            CreateTokenCommand createTokenCommand = new CreateTokenCommand(
                    UUID.randomUUID().toString(),
                    request.get_userId(),
                    generateNewToken(),
                    new Date()
            );
            commandGateway.send(createTokenCommand);
            return createTokenCommand.getToken();
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the token.";
        }
    }

    @PostMapping(path = "/create")
    public Object createCustomer(@RequestBody CreateCustomerRequestDTO request) {
        if(Objects.isNull(request))
            return "Customer can not be empty";
        try{
            CreateCustomerCommand createCustomerCommand = new CreateCustomerCommand(
                    UUID.randomUUID().toString(),
                    request.getFirstname(),
                    request.getLastname(),
                    request.getMail(),
                    request.getLogin(),
                    request.getPassword(),
                    new Date(),
                    true,
                    request.getAccountType(),
                    true,
                    false
                    );
            commandGateway.send(createCustomerCommand);
            return createCustomerCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the customer.";
        }
    }

    @PostMapping(path = "/signup")
    public Object createCustomerSignUp(@RequestBody CreateCustomerSignUpRequestDTO request) {
        if(Objects.isNull(request))
            return "Customer can not be empty";
        try{
            CreateCustomerSignUpCommand createCustomerSignUpCommand =new CreateCustomerSignUpCommand(
                    UUID.randomUUID().toString(),
                    request.getFirstname(),
                    request.getLastname(),
                    request.getPhoneNumber(),
                    mapIDsToAddresses(request.getAddresses()),
                    request.getFunction(),
                    request.getMail(),
                    request.getLogin(),
                    request.getPassword(),
                    new Date(),
                    false,
                    "signup",
                    true,
                    false
            );
            commandGateway.send(createCustomerSignUpCommand);
           // return createCustomerSignUpCommand;


            //create token for this customer
            CreateTokenCommand createTokenCommand=new CreateTokenCommand(
                    UUID.randomUUID().toString(),
                    createCustomerSignUpCommand.getId(),
                    generateNewToken(),
                    new Date()
                    );
            commandGateway.send(createTokenCommand);
            Object data = createCustomerSignUpCommand;
            Object[] customerToken = { data , createTokenCommand.getToken() };
            return customerToken;

        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the customer.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateCustomer(@RequestBody UpdateCustomerRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Customer can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateCustomerCommand updateCustomerCommand = new UpdateCustomerCommand(
                    request.getId(),
                    request.getFirstname(),
                    request.getLastname(),
                    request.getPhoneNumber(),
                    mapIDsToAddresses(request.getAddresses()),
                    request.getFunction(),
                    request.getMail(),
                    request.getLogin(),
                    request.getPassword(),
                    request.getActive(),
                    request.getDeleted()
                );
                commandGateway.send(updateCustomerCommand);
                return updateCustomerCommand;
            }else
                return "Customer not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating Customer with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{idCustomer}")
    public Object deleteCustomer(@PathVariable String idCustomer) {
        if(idCustomer.isEmpty())
            return "ID Customer can not be empty";
        try {
            DeleteCustomerCommand deleteCustomerCommand = new DeleteCustomerCommand(idCustomer);
            commandGateway.send(deleteCustomerCommand);
            return deleteCustomerCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete customer with id " + idCustomer;
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity=new ResponseEntity<String>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    private List<Address> mapIDsToAddresses(List<Address> addresses) {
        if (Objects.isNull(addresses))
            return null;
        else {
            return addresses.stream().map(sc -> new Address(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getAddress(),
                    sc.getPostalCode(),
                    sc.getCity()
            )).collect(Collectors.toList());
        }
    }
    private static String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
