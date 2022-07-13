package com.example.servicecategorywrite.aggregates;

import coreapi.commands.CreateCategoryCommand;
import coreapi.commands.DeleteCategoryCommand;
import coreapi.commands.UpdateCategoryCommand;
import coreapi.events.CreatedCategoryEvent;
import coreapi.events.DeletedCategoryEvent;
import coreapi.events.UpdatedCategoryEvent;
import coreapi.models.SubCategory;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

@Slf4j
@Aggregate
public class CategoryAggregate {
    @AggregateIdentifier
    private String idCategory;
    private String categoryName;
    private String image;
    private Boolean active;
    private List<SubCategory> categories;

    public CategoryAggregate(){}
    //Function de decision
    @CommandHandler
    public CategoryAggregate(CreateCategoryCommand createCategoryCommand) {
        log.info("CreateCategoryCommand received");
        if(createCategoryCommand.getCategoryName().isEmpty()) throw new RuntimeException("Title category can not be empty");
        AggregateLifecycle.apply(new CreatedCategoryEvent(
                createCategoryCommand.getId(),
                createCategoryCommand.getCategoryName(),
                createCategoryCommand.getImage(),
                createCategoryCommand.getActive(),
                createCategoryCommand.getSubCategories()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedCategoryEvent event) {
        log.info("CreatedCategoryEvent occured");
        this.idCategory=event.getId();
        this.categoryName= event.getCategoryName();
        this.image=event.getImage();
        this.active=event.getActive();
        this.categories=event.getSubCategories();

    }

    //function de decision
    @CommandHandler
    public void handle(UpdateCategoryCommand updateCategoryCommand) {
        log.info("updateCategoryCommand received");
        if(updateCategoryCommand.getCategoryName().isEmpty()) throw new RuntimeException("Title category can not be empty");
        AggregateLifecycle.apply(new UpdatedCategoryEvent(
                updateCategoryCommand.getId(),
                updateCategoryCommand.getCategoryName(),
                updateCategoryCommand.getImage(),
                updateCategoryCommand.getActive(),
                updateCategoryCommand.getSubCategories()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedCategoryEvent event) {
        log.info("UpdatedCategoryEvent occured");
        this.idCategory= event.getId();
        this.categoryName=event.getCategoryName();
        this.image=event.getImage();
        this.active=event.getActive();
        this.categories=event.getSubCategories();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteCategoryCommand deleteCategoryCommand) {
        log.info("deleteCategoryCommand received");
        if(deleteCategoryCommand.getId().isEmpty()) throw new RuntimeException("Id category can not be empty");
        AggregateLifecycle.apply(new DeletedCategoryEvent(
                deleteCategoryCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedCategoryEvent event) {
        log.info("DeletedCategoryEvent occured");
        this.idCategory=event.getId();
    }
}
