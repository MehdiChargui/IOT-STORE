package com.example.servicepaymentmethodewrite.aggregates;

import coreapi.commands.CreatePaymentMethodCommand;
import coreapi.commands.DeletePaymentMethodCommand;
import coreapi.commands.UpdatePaymentMethodCommand;
import coreapi.events.CreatedPaymentMethodEvent;
import coreapi.events.DeletedPaymentMethodEvent;
import coreapi.events.UpdatedPaymentMethodEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Aggregate
public class PaymentMethodAggregate {
    @AggregateIdentifier
    private String paymentMethodId;
    private String name;
    private Boolean active;
    private Boolean deleted;

    public PaymentMethodAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public PaymentMethodAggregate(CreatePaymentMethodCommand createPaymentMethodCommand) {
        log.info("CreatePaymentMethodCommand received");
        if(createPaymentMethodCommand.getName().isEmpty()) throw new RuntimeException("Title PaymentMethod can not be empty");
        AggregateLifecycle.apply(new CreatedPaymentMethodEvent(
                createPaymentMethodCommand.getId(),
                createPaymentMethodCommand.getName(),
                createPaymentMethodCommand.getActive(),
                createPaymentMethodCommand.getDeleted()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedPaymentMethodEvent event) {
        log.info("CreatedPaymentMethodEvent occured");
        this.paymentMethodId=event.getId();
        this.name=event.getName();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdatePaymentMethodCommand updatePaymentMethodCommand) {
        log.info("UpdatePaymentMethodCommand received");
        if(updatePaymentMethodCommand.getName().isEmpty()) throw new RuntimeException("Title PaymentMethod can not be empty");
        AggregateLifecycle.apply(new UpdatedPaymentMethodEvent(
                updatePaymentMethodCommand.getId(),
                updatePaymentMethodCommand.getName(),
                updatePaymentMethodCommand.getActive(),
                updatePaymentMethodCommand.getDeleted()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedPaymentMethodEvent event) {
        log.info("UpdatedPaymentMethodEvent occured");
        this.paymentMethodId=event.getId();
        this.name=event.getName();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
    }

    //function de decision
    @CommandHandler
    public void handle(DeletePaymentMethodCommand deletePaymentMethodCommand) {
        log.info("DeletePaymentMethodCommand received");
        if(deletePaymentMethodCommand.getId().isEmpty()) throw new RuntimeException("Id PaymentMethod can not be empty");
        AggregateLifecycle.apply(new DeletedPaymentMethodEvent(
                deletePaymentMethodCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedPaymentMethodEvent event) {
        log.info("DeletedPaymentMethodEvent occured");
        this.paymentMethodId=event.getId();
    }


}
