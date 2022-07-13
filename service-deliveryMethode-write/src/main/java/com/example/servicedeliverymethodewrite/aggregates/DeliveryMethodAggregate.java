package com.example.servicedeliverymethodewrite.aggregates;

import coreapi.commands.CreateDeliveryMethodCommand;
import coreapi.commands.DeleteDeliveryMethodCommand;
import coreapi.commands.UpdateDeliveryMethodCommand;
import coreapi.events.CreatedDeliveryMethodEvent;
import coreapi.events.DeletedDeliveryMethodEvent;
import coreapi.events.UpdatedDeliveryMethodEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class DeliveryMethodAggregate {
    @AggregateIdentifier
    private String DeliveryMethodId;
    private String name;
    private Boolean active;
    private Boolean deleted;
    public DeliveryMethodAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public DeliveryMethodAggregate(CreateDeliveryMethodCommand createDeliveryMethodCommand) {
        log.info("CreateDeliveryMethodCommand received");
        if(createDeliveryMethodCommand.getName().isEmpty()) throw new RuntimeException("Title DeliveryMethod can not be empty");
        AggregateLifecycle.apply(new CreatedDeliveryMethodEvent(
                createDeliveryMethodCommand.getId(),
                createDeliveryMethodCommand.getName(),
                createDeliveryMethodCommand.getActive(),
                createDeliveryMethodCommand.getDeleted()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedDeliveryMethodEvent event) {
        log.info("CreatedDeliveryMethodEvent occured");
        this.DeliveryMethodId=event.getId();
        this.name=event.getName();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateDeliveryMethodCommand updateDeliveryMethodCommand) {
        log.info("UpdateDeliveryMethodCommand received");
        if(updateDeliveryMethodCommand.getName().isEmpty()) throw new RuntimeException("Title DeliveryMethod can not be empty");
        AggregateLifecycle.apply(new UpdatedDeliveryMethodEvent(
                updateDeliveryMethodCommand.getId(),
                updateDeliveryMethodCommand.getName(),
                updateDeliveryMethodCommand.getActive(),
                updateDeliveryMethodCommand.getDeleted()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedDeliveryMethodEvent event) {
        log.info("UpdatedDeliveryMethodEvent occured");
        this.DeliveryMethodId=event.getId();
        this.name=event.getName();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteDeliveryMethodCommand deleteDeliveryMethodCommand) {
        log.info("DeleteDeliveryMethodCommand received");
        if(deleteDeliveryMethodCommand.getId().isEmpty()) throw new RuntimeException("Id DeliveryMethod can not be empty");
        AggregateLifecycle.apply(new DeletedDeliveryMethodEvent(
                deleteDeliveryMethodCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedDeliveryMethodEvent event) {
        log.info("DeletedDeliveryMethodEvent occured");
        this.DeliveryMethodId=event.getId();
    }


}
