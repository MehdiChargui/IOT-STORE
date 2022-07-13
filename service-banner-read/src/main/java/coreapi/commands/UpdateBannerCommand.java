package coreapi.commands;

import lombok.Getter;

public class UpdateBannerCommand extends BaseCommand<String>{
    @Getter private String title;
    @Getter private String description;
    @Getter private String location;
    @Getter private String type;
    @Getter private String link;
    @Getter private String image;
    @Getter private Boolean active;

    public UpdateBannerCommand(String id, String title, String description, String location, String type, String link,
                               String image, Boolean active) {
        super(id);
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.link = link;
        this.image = image;
        this.active = active;
    }

}
