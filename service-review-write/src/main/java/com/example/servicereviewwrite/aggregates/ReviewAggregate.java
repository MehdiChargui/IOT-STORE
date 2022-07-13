package com.example.servicereviewwrite.aggregates;

import coreapi.commands.CreateReviewCommand;
import coreapi.commands.DeleteReviewCommand;
import coreapi.commands.UpdateReviewCommand;
import coreapi.events.CreatedReviewEvent;
import coreapi.events.DeletedReviewEvent;
import coreapi.events.UpdatedReviewEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Slf4j
@Aggregate
public class ReviewAggregate {
    @AggregateIdentifier
    private String reveiwId;
    private String customer;
    private String article;
    private int note;
    private String comment;
    private Date creationDate;
    private Boolean active;

    public ReviewAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public ReviewAggregate(CreateReviewCommand createReviewCommand) {
        log.info("CreateReviewCommand received");
        //logique metier
        AggregateLifecycle.apply(new CreatedReviewEvent(
                createReviewCommand.getId(),
                createReviewCommand.getCustomer(),
                createReviewCommand.getArticle(),
                createReviewCommand.getNote(),
                createReviewCommand.getComment(),
                createReviewCommand.getCreationDate(),
                createReviewCommand.getActive()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedReviewEvent event) {
        log.info("CreatedReviewEvent occured");
        this.reveiwId=event.getId();
        this.customer=event.getCustomer();
        this.article=event.getArticle();
        this.note=event.getNote();
        this.comment=event.getComment();
        this.creationDate=event.getCreationDate();
        this.active=event.getActive();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateReviewCommand updateReviewCommand) {
        log.info("UpdateReviewCommand received");
        AggregateLifecycle.apply(new UpdatedReviewEvent(
                updateReviewCommand.getId(),
                updateReviewCommand.getCustomer(),
                updateReviewCommand.getArticle(),
                updateReviewCommand.getNote(),
                updateReviewCommand.getComment(),
                updateReviewCommand.getActive()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedReviewEvent event) {
        log.info("UpdatedReviewEvent occured");
        this.reveiwId=event.getId();
        this.customer=event.getCustomer();
        this.article=event.getArticle();
        this.note=event.getNote();
        this.comment=event.getComment();
        this.active=event.getActive();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteReviewCommand deleteReviewCommand) {
        log.info("DeleteDeliveryMethodCommand received");
        if(deleteReviewCommand.getId().isEmpty()) throw new RuntimeException("Id DeliveryMethod can not be empty");
        AggregateLifecycle.apply(new DeletedReviewEvent(
                deleteReviewCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedReviewEvent event) {
        log.info("DeletedDeliveryMethodEvent occured");
        this.reveiwId=event.getId();
    }


}
