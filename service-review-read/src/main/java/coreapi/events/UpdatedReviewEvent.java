package coreapi.events;

import lombok.Getter;

public class UpdatedReviewEvent extends BaseEvent<String> {

    @Getter private String customer;
    @Getter private String article;
    @Getter private int note;
    @Getter private String comment;
    @Getter private Boolean active;

    public UpdatedReviewEvent(String id, String customer, String article, int note, String comment, Boolean active) {
        super(id);
        this.customer = customer;
        this.article = article;
        this.note = note;
        this.comment = comment;
        this.active = active;
    }


}

