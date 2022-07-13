package com.example.servicecustomerread.controllers;

import com.example.servicecustomerread.entites.Customer;
import coreapi.querys.GetAllCustomerQuery;
import coreapi.querys.GetCustomerByIdQuery;
import coreapi.querys.GetCustomerByLoginAndPasswordQuery;
import coreapi.querys.GetCustomerByMailQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/customer/read")
public class CustomerQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allCustomers")
    public Object CustomerList() {
        try{
            List<Customer> response =queryGateway.query(new GetAllCustomerQuery(), ResponseTypes.multipleInstancesOf(Customer.class)).join();
            return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the customer.";
        }
    }

    @GetMapping("/getById/{id}")
    public Object getCustomer(@PathVariable String id) {
        try {
            Customer response =queryGateway.query(new GetCustomerByIdQuery(id),ResponseTypes.instanceOf(Customer.class)).join();
            if(Objects.isNull(response))
                return "Customer not found with id " + id;
            else
                return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the customer.";
        }
    }

    @GetMapping("/getByMail/{mail}")
    public Object getCustomerByMail(@PathVariable String mail) {
        try{
            Customer response =queryGateway.query(new GetCustomerByMailQuery(mail),ResponseTypes.instanceOf(Customer.class)).join();
            if(Objects.isNull(response))
                return "Customer not found with mail " + mail;
            else
                return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the customer.";
        }
    }

    @GetMapping("/connect/{login}/{password}")
    public Object getCustomerByMailMdp(@PathVariable String login,@PathVariable String password) {
        try{
            Customer response =queryGateway.query(new GetCustomerByLoginAndPasswordQuery(login,password),ResponseTypes.instanceOf(Customer.class)).join();
            return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the customer.";
        }
    }

}
