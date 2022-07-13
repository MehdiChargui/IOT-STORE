package coreapi.events;


import lombok.Getter;

import java.util.Date;


public class CreatedReviewEvent extends BaseEvent<String> {

    @Getter private String customer;
    @Getter private String article;
    @Getter private int note;
    @Getter private String comment;
    @Getter private Date creationDate;
    @Getter private Boolean active;

    public CreatedReviewEvent(String id, String customer, String article, int note, String comment, Date creationDate, Boolean active) {
        super(id);
        this.customer = customer;
        this.article = article;
        this.note = note;
        this.comment = comment;
        this.creationDate = creationDate;
        this.active = active;
    }


}

