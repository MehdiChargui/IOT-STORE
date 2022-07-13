package com.example.servicebannerwrite.aggregates;

import coreapi.commands.CreateBannerCommand;
import coreapi.commands.DeleteBannerCommand;
import coreapi.commands.UpdateBannerCommand;
import coreapi.events.CreatedBannerEvent;
import coreapi.events.DeletedBannerEvent;
import coreapi.events.UpdatedBannerEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Date;

@Slf4j
@Aggregate
public class BannerAggregate {
    @AggregateIdentifier
    private String bannerId;
    private String title;
    private String description;
    private String location;
    private String type;
    private String link;
    private String image;
    private Date creationDate;
    private Boolean active;

    public BannerAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public BannerAggregate(CreateBannerCommand createBannerCommand) {
        log.info("CreateBannerCommand received");
        if(createBannerCommand.getTitle().isEmpty()) throw new RuntimeException("Title banner can not be empty");
        AggregateLifecycle.apply(new CreatedBannerEvent(
                createBannerCommand.getId(),
                createBannerCommand.getTitle(),
                createBannerCommand.getDescription(),
                createBannerCommand.getLocation(),
                createBannerCommand.getType(),
                createBannerCommand.getLink(),
                createBannerCommand.getImage(),
                createBannerCommand.getCreationDate(),
                createBannerCommand.getActive()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedBannerEvent event) {
        log.info("CreatedBannerEvent occured");
        this.bannerId=event.getId();
        this.title=event.getTitle();
        this.description=event.getDescription();
        this.location=event.getLocation();
        this.type=event.getType();
        this.link=event.getLink();
        this.image=event.getImage();
        this.creationDate=event.getCreationDate();
        this.active=event.getActive();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateBannerCommand updateBannerCommand) {
        log.info("UpdateBannerCommand received");
        if(updateBannerCommand.getTitle().isEmpty()) throw new RuntimeException("Title banner can not be empty");
        AggregateLifecycle.apply(new UpdatedBannerEvent(
                updateBannerCommand.getId(),
                updateBannerCommand.getTitle(),
                updateBannerCommand.getDescription(),
                updateBannerCommand.getLocation(),
                updateBannerCommand.getType(),
                updateBannerCommand.getLink(),
                updateBannerCommand.getImage(),
                updateBannerCommand.getActive()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedBannerEvent event) {
        log.info("UpdatedBannerEvent occured");
        this.bannerId=event.getId();
        this.title=event.getTitle();
        this.description=event.getDescription();
        this.location=event.getLocation();
        this.type=event.getType();
        this.link=event.getLink();
        this.image=event.getImage();
        this.active=event.getActive();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteBannerCommand deleteBannerCommand) {
        log.info("DeleteBannerCommand received");
        if(deleteBannerCommand.getId().isEmpty()) throw new RuntimeException("Id banner can not be empty");
        AggregateLifecycle.apply(new DeletedBannerEvent(
                deleteBannerCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedBannerEvent event) {
        log.info("DeletedBannerEvent occured");
        this.bannerId=event.getId();
    }


}
