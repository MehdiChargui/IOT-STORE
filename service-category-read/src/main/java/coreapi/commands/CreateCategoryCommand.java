package coreapi.commands;


import coreapi.models.SubCategory;
import lombok.Getter;

import java.util.List;

public class CreateCategoryCommand extends BaseCommand<String>{

    @Getter private String categoryName;
    @Getter private String image;
    @Getter private Boolean active;
    @Getter private List<SubCategory> subCategories;


    public CreateCategoryCommand(String id, String categoryName, String image, Boolean active, List<SubCategory> subCategories) {
        super(id);
        this.categoryName = categoryName;
        this.image = image;
        this.active = active;
        this.subCategories = subCategories;
    }

}

