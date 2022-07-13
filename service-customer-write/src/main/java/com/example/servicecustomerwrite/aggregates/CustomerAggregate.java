package com.example.servicecustomerwrite.aggregates;


import coreapi.commands.*;
import coreapi.events.*;
import coreapi.models.Address;
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
public class CustomerAggregate {
    @AggregateIdentifier
    private String id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private List<Address> addresses;
    private String function;
    private Date registrationDate;
    private String mail;
    private String login;
    private String password;
    private Boolean active;
    private Boolean mailVerified;
    private String accountType;
    private Boolean deleted;
    private String _userId;
    private String token;
    private Date createdAt;



    public CustomerAggregate(){}
    //Function de decision
    @CommandHandler
    public CustomerAggregate(CreateTokenCommand createTokenCommand) {
        log.info("CreateTokenCommand received");
        if(createTokenCommand.getId().isEmpty()) throw new RuntimeException("Id token can not be empty");
        AggregateLifecycle.apply(new CreatedTokenEvent(
                createTokenCommand.getId(),
                createTokenCommand.get_userId(),
                createTokenCommand.getToken(),
                createTokenCommand.getCreatedAt()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedTokenEvent event) {
        log.info("CreatedTokenEvent occured");
        this.id= event.getId();
        this._userId= event.get_userId();
        this.token= event.getToken();
        this.createdAt=event.getCreatedAt();
    }
    //Function de decision
    @CommandHandler
    public CustomerAggregate(CreateCustomerCommand createCustomerCommand) {
        log.info("CreateCustomerCommand received");
        if(createCustomerCommand.getLogin().isEmpty()) throw new RuntimeException("Login customer can not be empty");
        AggregateLifecycle.apply(new CreatedCustomerEvent(
                createCustomerCommand.getId(),
                createCustomerCommand.getFirstname(),
                createCustomerCommand.getLastname(),
                createCustomerCommand.getMail(),
                createCustomerCommand.getLogin(),
                createCustomerCommand.getPassword(),
                createCustomerCommand.getRegistrationDate(),
                createCustomerCommand.getMailVerified(),
                createCustomerCommand.getAccountType(),
                createCustomerCommand.getActive(),
                createCustomerCommand.getDeleted()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedCustomerEvent event) {
        log.info("CreatedCustomerEvent occured");
        this.id=event.getId();
        this.firstname=event.getFirstname();
        this.lastname=event.getLastname();
        this.mail=event.getMail();
        this.login=event.getLogin();
        this.password= event.getPassword();
        this.registrationDate=event.getRegistrationDate();
        this.mailVerified=event.getMailVerified();
        this.accountType= event.getAccountType();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateCustomerCommand updateCustomerCommand) {
        log.info("updateCustomerCommand received");
        if(updateCustomerCommand.getId().isEmpty()) throw new RuntimeException("Id customer can not be empty");
        AggregateLifecycle.apply(new UpdatedCustomerEvent(
                updateCustomerCommand.getId(),
                updateCustomerCommand.getFirstname(),
                updateCustomerCommand.getLastname(),
                updateCustomerCommand.getPhoneNumber(),
                updateCustomerCommand.getAddresses(),
                updateCustomerCommand.getFunction(),
                updateCustomerCommand.getMail(),
                updateCustomerCommand.getLogin(),
                updateCustomerCommand.getPassword(),
                updateCustomerCommand.getActive(),
                updateCustomerCommand.getDeleted()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedCustomerEvent event) {
        log.info("UpdatedCustomerEvent occured");
        this.id= event.getId();
        this.firstname=event.getFirstname();
        this.lastname=event.getLastname();
        this.phoneNumber=event.getPhoneNumber();
        this.addresses=event.getAddresses();
        this.function=event.getFunction();
        this.mail= event.getMail();
        this.login= event.getLogin();
        this.password= event.getPassword();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public CustomerAggregate(CreateCustomerSignUpCommand createCustomerSignUpCommand) {
        log.info("CreateCustomerSignUpCommand received");
        if(createCustomerSignUpCommand.getId().isEmpty()) throw new RuntimeException("Id customer can not be empty");
        AggregateLifecycle.apply(new CreatedCustomerSignUpEvent(
                createCustomerSignUpCommand.getId(),
                createCustomerSignUpCommand.getFirstname(),
                createCustomerSignUpCommand.getLastname(),
                createCustomerSignUpCommand.getPhoneNumber(),
                createCustomerSignUpCommand.getAddresses(),
                createCustomerSignUpCommand.getFunction(),
                createCustomerSignUpCommand.getMail(),
                createCustomerSignUpCommand.getLogin(),
                createCustomerSignUpCommand.getPassword(),
                createCustomerSignUpCommand.getRegistrationDate(),
                createCustomerSignUpCommand.getMailVerified(),
                createCustomerSignUpCommand.getAccountType(),
                createCustomerSignUpCommand.getActive(),
                createCustomerSignUpCommand.getDeleted()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(CreatedCustomerSignUpEvent event) {
        log.info("CreatedCustomerSignUpEvent occured");
        this.id= event.getId();
        this.firstname=event.getFirstname();
        this.lastname=event.getLastname();
        this.phoneNumber=event.getPhoneNumber();
        this.addresses=event.getAddresses();
        this.function=event.getFunction();
        this.mail= event.getMail();
        this.login= event.getLogin();
        this.password= event.getPassword();
        this.registrationDate=event.getRegistrationDate();
        this.mailVerified=event.getMailVerified();
        this.accountType= event.getAccountType();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteCustomerCommand deleteCustomerCommand) {
        log.info("deleteCustomerCommand received");
        if(deleteCustomerCommand.getId().isEmpty()) throw new RuntimeException("Id customer can not be empty");
        AggregateLifecycle.apply(new DeletedCustomerEvent(
                deleteCustomerCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedCustomerEvent event) {
        log.info("DeletedCustomerEvent occured");
        this.id=event.getId();
    }


}
