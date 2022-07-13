package com.example.servicearticlewrite.aggregates;

import coreapi.commands.CreateArticleCommand;
import coreapi.commands.DeleteArticleCommand;
import coreapi.commands.UpdateArticleCommand;
import coreapi.events.CreatedArticleEvent;
import coreapi.events.DeletedArticleEvent;
import coreapi.events.UpdatedArticleEvent;
import coreapi.models.*;
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
public class ArticleAggregate {
    @AggregateIdentifier
    private String ArticleId;
    private String title;
    private String description;
    private String reference;
    private String availability;
    private double price;
    private double tax;
    private int quantity;
    private Date creationDate;
    private Boolean active;
    private Boolean deleted;
    private Discount discount;
    private List<Image> images;
    private List<Video> videos;
    private List<Feature> features;
    private List<Category> categories;
    private List<PriceHistory> priceHistory;

    public ArticleAggregate(){
        //Required by axon
    }

    //Function de decision
    @CommandHandler
    public ArticleAggregate(CreateArticleCommand createArticleCommand) {
        log.info("CreateArticleCommand received");
        //logique métier
        AggregateLifecycle.apply(new CreatedArticleEvent(
                createArticleCommand.getId(),
                createArticleCommand.getTitle(),
                createArticleCommand.getDescription(),
                createArticleCommand.getReference(),
                createArticleCommand.getAvailability(),
                createArticleCommand.getPrice(),
                createArticleCommand.getTax(),
                createArticleCommand.getQuantity(),
                createArticleCommand.getCreationDate(),
                createArticleCommand.getActive(),
                createArticleCommand.getDeleted(),
                createArticleCommand.getDiscount(),
                createArticleCommand.getImages(),
                createArticleCommand.getVideos(),
                createArticleCommand.getFeatures(),
                createArticleCommand.getCategories(),
                createArticleCommand.getPriceHistory()
        ));
    }

    //Function de d evaluation
    @EventSourcingHandler
    public void on(CreatedArticleEvent event) {
        log.info("CreatedArticleEvent occured");
        this.ArticleId=event.getId();
        this.title= event.getTitle();
        this.description=event.getDescription();
        this.reference= event.getReference();
        this.availability= event.getAvailability();
        this.price= event.getPrice();
        this.tax= event.getTax();
        this.quantity=event.getQuantity();
        this.creationDate=event.getCreationDate();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
        this.discount=event.getDiscount();
        this.images=event.getImages();
        this.videos=event.getVideos();
        this.features=event.getFeatures();
        this.categories=event.getCategories();
        this.priceHistory=event.getPriceHistory();
    }

    //function de decision
    @CommandHandler
    public void handle(UpdateArticleCommand updateArticleCommand) {
        log.info("UpdateArticleCommand received");
        if(updateArticleCommand.getId().isEmpty()) throw new RuntimeException("Id Article can not be empty");
        AggregateLifecycle.apply(new UpdatedArticleEvent(
                updateArticleCommand.getId(),
                updateArticleCommand.getTitle(),
                updateArticleCommand.getDescription(),
                updateArticleCommand.getReference(),
                updateArticleCommand.getAvailability(),
                updateArticleCommand.getPrice(),
                updateArticleCommand.getTax(),
                updateArticleCommand.getQuantity(),
                updateArticleCommand.getActive(),
                updateArticleCommand.getDeleted(),
                updateArticleCommand.getDiscount(),
                updateArticleCommand.getImages(),
                updateArticleCommand.getVideos(),
                updateArticleCommand.getFeatures(),
                updateArticleCommand.getCategories(),
                updateArticleCommand.getPriceHistory()
        ));
    }

    //function d evolution
    @EventSourcingHandler
    public void on(UpdatedArticleEvent event) {
        log.info("UpdatedArticleEvent occured");
        this.ArticleId=event.getId();
        this.title= event.getTitle();
        this.description=event.getDescription();
        this.reference= event.getReference();
        this.availability= event.getAvailability();
        this.price= event.getPrice();
        this.tax= event.getTax();
        this.quantity=event.getQuantity();
        this.active=event.getActive();
        this.deleted=event.getDeleted();
        this.discount=event.getDiscount();
        this.images=event.getImages();
        this.videos=event.getVideos();
        this.features=event.getFeatures();
        this.categories=event.getCategories();
        this.priceHistory=event.getPriceHistory();
    }

    //function de decision
    @CommandHandler
    public void handle(DeleteArticleCommand deleteArticleCommand) {
        log.info("DeleteArticleCommand received");
        if(deleteArticleCommand.getId().isEmpty()) throw new RuntimeException("Id Article can not be empty");
        AggregateLifecycle.apply(new DeletedArticleEvent(
                deleteArticleCommand.getId()
        ));
    }

    //function d'evolution
    @EventSourcingHandler
    public void on(DeletedArticleEvent event) {
        log.info("DeletedArticleEvent occured");
        this.ArticleId=event.getId();
    }
}
