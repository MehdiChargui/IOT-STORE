package coreapi.events;

import coreapi.models.SubCategory;
import lombok.Getter;

import java.util.List;

public class UpdatedCategoryEvent extends BaseEvent<String> {

    @Getter private String categoryName;
    @Getter private String image;
    @Getter private Boolean active;
    @Getter private List<SubCategory> subCategories;

    public UpdatedCategoryEvent(String id, String categoryName, String image, Boolean active, List<SubCategory> subCategories) {
        super(id);
        this.categoryName = categoryName;
        this.image = image;
        this.active = active;
        this.subCategories = subCategories;
    }

}
