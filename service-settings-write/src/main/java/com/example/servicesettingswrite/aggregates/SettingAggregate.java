package com.example.servicesettingswrite.aggregates;

import coreapi.commands.CreateSettingCommand;
import coreapi.commands.DeleteSettingCommand;
import coreapi.commands.UpdateSettingCommand;
import coreapi.events.CreatedSettingEvent;
import coreapi.events.DeletedSettingEvent;
import coreapi.events.UpdatedSettingEvent;
import coreapi.models.Links;
import coreapi.models.MailList;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Slf4j
@Aggregate
public class SettingAggregate {
    @AggregateIdentifier
    private String settingId;
    private String description;
    private List<MailList> mailList;
    private List<Links> links;

    public SettingAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public SettingAggregate(CreateSettingCommand createSettingCommand) {
        log.info("CreateSettingCommand received");
        //logique métier
        AggregateLifecycle.apply(new CreatedSettingEvent(
                createSettingCommand.getId(),
                createSettingCommand.getDescription(),
                createSettingCommand.getMailList(),
                createSettingCommand.getLinks()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedSettingEvent event) {
        log.info("CreatedSettingEvent occured");
        this.settingId=event.getId();
        this.description=event.getDescription();
        this.mailList=event.getMailList();
        this.links=event.getLinks();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateSettingCommand updateSettingCommand) {
        log.info("UpdateSettingCommand received");
        if(updateSettingCommand.getId().isEmpty()) throw new RuntimeException("Id Setting can not be empty");
        AggregateLifecycle.apply(new UpdatedSettingEvent(
                updateSettingCommand.getId(),
                updateSettingCommand.getDescription(),
                updateSettingCommand.getMailList(),
                updateSettingCommand.getLinks()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedSettingEvent event) {
        log.info("UpdatedSettingEvent occured");
        this.settingId=event.getId();
        this.description=event.getDescription();
        this.mailList=event.getMailList();
        this.links=event.getLinks();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteSettingCommand deleteSettingCommand) {
        log.info("DeleteSettingCommand received");
        if(deleteSettingCommand.getId().isEmpty()) throw new RuntimeException("Id Setting can not be empty");
        AggregateLifecycle.apply(new DeletedSettingEvent(
                deleteSettingCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedSettingEvent event) {
        log.info("DeletedSettingEvent occured");
        this.settingId=event.getId();
    }
}
