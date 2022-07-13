package com.example.serviceadminwrite.aggregates;

import coreapi.commands.CreateAdminCommand;
import coreapi.commands.DeleteAdminCommand;
import coreapi.commands.UpdateAdminCommand;
import coreapi.events.CreatedAdminEvent;
import coreapi.events.DeletedAdminEvent;
import coreapi.events.UpdatedAdminEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class AdminAggregate {
    @AggregateIdentifier
    private String adminId;
    private String firstname;
    private String lastname;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String function;
    private String mail;
    private String login;
    private String password;
    private int role;
    private Boolean deleted;


    public AdminAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public AdminAggregate(CreateAdminCommand createAdminCommand) {
        log.info("CreateAdminCommand received");
        //logique métier
        AggregateLifecycle.apply(new CreatedAdminEvent(
                createAdminCommand.getId(),
                createAdminCommand.getFirstname(),
                createAdminCommand.getLastname(),
                createAdminCommand.getAddress(),
                createAdminCommand.getPostalCode(),
                createAdminCommand.getPhoneNumber(),
                createAdminCommand.getFunction(),
                createAdminCommand.getMail(),
                createAdminCommand.getLogin(),
                createAdminCommand.getPassword(),
                createAdminCommand.getRole(),
                createAdminCommand.getDeleted()

        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedAdminEvent event) {
        log.info("CreatedAdminEvent occured");
        this.adminId=event.getId();
        this.firstname=event.getFirstname();
        this.lastname=event.getLastname();
        this.address=event.getAddress();
        this.postalCode= event.getPostalCode();
        this.phoneNumber=event.getPhoneNumber();
        this.function= event.getFunction();
        this.mail=event.getMail();
        this.login=event.getLogin();
        this.password=event.getPassword();
        this.role=event.getRole();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateAdminCommand updateAdminCommand) {
        log.info("UpdateAdminCommand received");
        if(updateAdminCommand.getId().isEmpty()) throw new RuntimeException("Id Admin can not be empty");
        AggregateLifecycle.apply(new UpdatedAdminEvent(
                updateAdminCommand.getId(),
                updateAdminCommand.getFirstname(),
                updateAdminCommand.getLastname(),
                updateAdminCommand.getAddress(),
                updateAdminCommand.getPostalCode(),
                updateAdminCommand.getPhoneNumber(),
                updateAdminCommand.getFunction(),
                updateAdminCommand.getMail(),
                updateAdminCommand.getLogin(),
                updateAdminCommand.getPassword(),
                updateAdminCommand.getRole(),
                updateAdminCommand.getDeleted()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedAdminEvent event) {
        log.info("UpdatedAdminEvent occured");
        this.adminId=event.getId();
        this.firstname=event.getFirstname();
        this.lastname=event.getLastname();
        this.address=event.getAddress();
        this.postalCode= event.getPostalCode();
        this.phoneNumber=event.getPhoneNumber();
        this.function= event.getFunction();
        this.mail=event.getMail();
        this.login=event.getLogin();
        this.password=event.getPassword();
        this.role=event.getRole();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteAdminCommand deleteAdminCommand) {
        log.info("DeleteAdminCommand received");
        if(deleteAdminCommand.getId().isEmpty()) throw new RuntimeException("Id Admin can not be empty");
        AggregateLifecycle.apply(new DeletedAdminEvent(
                deleteAdminCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedAdminEvent event) {
        log.info("DeletedAdminEvent occured");
        this.adminId=event.getId();
    }
}
