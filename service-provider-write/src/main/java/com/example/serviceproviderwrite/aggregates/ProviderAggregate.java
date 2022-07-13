package com.example.serviceproviderwrite.aggregates;
import coreapi.commands.CreateProviderCommand;
import coreapi.commands.DeleteProviderCommand;
import coreapi.commands.UpdateProviderCommand;
import coreapi.events.CreatedProviderEvent;
import coreapi.events.DeletedProviderEvent;
import coreapi.events.UpdatedProviderEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Slf4j
@Aggregate
public class ProviderAggregate {
    @AggregateIdentifier
    private String providerId;
    private String designation;
    private String description;
    private String phoneNumber;
    private String mail;
    private String link;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private Date creationDate;
    private Boolean active;
    private Boolean deleted;

    public ProviderAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public ProviderAggregate(CreateProviderCommand createProviderCommand) {
        log.info("CreateProviderCommand received");
        if(createProviderCommand.getDesignation().isEmpty()) throw new RuntimeException("Title Provider can not be empty");
        AggregateLifecycle.apply(new CreatedProviderEvent(
                createProviderCommand.getId(),
                createProviderCommand.getDesignation(),
                createProviderCommand.getDescription(),
                createProviderCommand.getPhoneNumber(),
                createProviderCommand.getMail(),
                createProviderCommand.getLink(),
                createProviderCommand.getAddress(),
                createProviderCommand.getPostalCode(),
                createProviderCommand.getCity(),
                createProviderCommand.getCountry(),
                createProviderCommand.getCreationDate(),
                createProviderCommand.getActive(),
                createProviderCommand.getDeleted()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedProviderEvent event) {
        log.info("CreatedProviderEvent occured");
        this.providerId=event.getId();
        this.designation= event.getDesignation();
        this.description= event.getDescription();
        this.phoneNumber= event.getPhoneNumber();
        this.mail= event.getMail();
        this.link= event.getLink();
        this.address= event.getAddress();
        this.postalCode= event.getPostalCode();
        this.city= event.getCity();
        this.country= event.getCountry();
        this.creationDate=event.getCreationDate();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateProviderCommand updateProviderCommand) {
        log.info("UpdateProviderCommand received");
        if(updateProviderCommand.getDesignation().isEmpty()) throw new RuntimeException("Title Provider can not be empty");
        AggregateLifecycle.apply(new UpdatedProviderEvent(
                updateProviderCommand.getId(),
                updateProviderCommand.getDesignation(),
                updateProviderCommand.getDescription(),
                updateProviderCommand.getPhoneNumber(),
                updateProviderCommand.getMail(),
                updateProviderCommand.getLink(),
                updateProviderCommand.getAddress(),
                updateProviderCommand.getPostalCode(),
                updateProviderCommand.getCity(),
                updateProviderCommand.getCountry(),
                updateProviderCommand.getActive(),
                updateProviderCommand.getDeleted()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedProviderEvent event) {
        log.info("UpdatedProviderEvent occured");
        this.providerId=event.getId();
        this.designation= event.getDesignation();
        this.description= event.getDescription();
        this.phoneNumber= event.getPhoneNumber();
        this.mail= event.getMail();
        this.link= event.getLink();
        this.address= event.getAddress();
        this.postalCode= event.getPostalCode();
        this.city= event.getCity();
        this.country= event.getCountry();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteProviderCommand deleteProviderCommand) {
        log.info("DeleteProviderCommand received");
        if(deleteProviderCommand.getId().isEmpty()) throw new RuntimeException("Id Provider can not be empty");
        AggregateLifecycle.apply(new DeletedProviderEvent(
                deleteProviderCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedProviderEvent event) {
        log.info("DeletedProviderEvent occured");
        this.providerId=event.getId();
    }


}
