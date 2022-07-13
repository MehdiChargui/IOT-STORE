package coreapi.events;

import lombok.Getter;

import java.util.Date;

public class CreatedBannerEvent extends BaseEvent<String> {
    @Getter private String title;
    @Getter private String description;
    @Getter private String location;
    @Getter private String type;
    @Getter private String link;
    @Getter private String image;
    @Getter private Date creationDate;
    @Getter private Boolean active;

    public CreatedBannerEvent(String id, String title, String description, String location, String type, String link,
                              String image, Date creationDate, Boolean active) {
        super(id);
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.link = link;
        this.image = image;
        this.creationDate = creationDate;
        this.active = active;
    }

}
