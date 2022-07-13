package com.example.servicecustomerread.services;

import com.example.servicecustomerread.entites.Customer;
import com.example.servicecustomerread.repositories.CustomerRepository;
import coreapi.events.CreatedCustomerEvent;
import coreapi.events.CreatedCustomerSignUpEvent;
import coreapi.events.DeletedCustomerEvent;
import coreapi.events.UpdatedCustomerEvent;
import coreapi.querys.GetAllCustomerQuery;
import coreapi.querys.GetCustomerByIdQuery;
import coreapi.querys.GetCustomerByLoginAndPasswordQuery;
import coreapi.querys.GetCustomerByMailQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CustomerServiceHandler {
    @Autowired
    private CustomerRepository customerRepository;



    @EventHandler
    public void on(CreatedCustomerEvent event){
        log.info("CreatedCustomerEvent recived");
        Customer customer = new Customer(
                event.getId(),
                event.getFirstname(),
                event.getLastname(),
                null,
                null,
                null,
                event.getMail(),
                event.getLogin(),
                event.getPassword(),
                event.getRegistrationDate(),
                event.getMailVerified(),
                event.getAccountType(),
                event.getActive(),
                event.getDeleted()
        );
        customerRepository.save(customer);
    }
    @EventHandler
    public void on(CreatedCustomerSignUpEvent event){
        log.info("CreatedCustomerSignUpEvent recived");
        Customer customer = new Customer(
                event.getId(),
                event.getFirstname(),
                event.getLastname(),
                event.getPhoneNumber(),
                event.getAddresses(),
                event.getFunction(),
                event.getMail(),
                event.getLogin(),
                event.getPassword(),
                event.getRegistrationDate(),
                event.getMailVerified(),
                event.getAccountType(),
                event.getActive(),
                event.getDeleted()
        );
        customerRepository.save(customer);
    }

    @EventHandler
    public void on(UpdatedCustomerEvent event) {
        log.info("UpdatedCustomerEvent recived");
        Customer customer = customerRepository.findById(event.getId()).get();
        customer.setFirstname(event.getFirstname());
        customer.setLastname(event.getLastname());
        customer.setAddresses(event.getAddresses());
        customer.setFunction(event.getFunction());
        customer.setMail(event.getMail());
        customer.setPhoneNumber(event.getPhoneNumber());
        customer.setLogin(event.getLogin());
        customer.setPassword(event.getPassword());
        customer.setActive(event.getActive());
        customer.setDeleted(event.getDeleted());
        customerRepository.save(customer);
    }

    @EventHandler
    public void on(DeletedCustomerEvent event) {
        log.info("DeletedCustomerEvent recived");
        customerRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Customer> on(GetAllCustomerQuery query){
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer on(GetCustomerByIdQuery query){
        return customerRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public Customer on(GetCustomerByMailQuery query){
        return customerRepository.findByMail(query.getMail());
    }

    @QueryHandler
    public Customer on(GetCustomerByLoginAndPasswordQuery query){
        return customerRepository.findByLoginAndPassword(query.getLogin(), query.getPassword());
    }


}
