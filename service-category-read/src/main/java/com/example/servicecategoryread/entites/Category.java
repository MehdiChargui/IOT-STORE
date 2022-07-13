package com.example.servicecategoryread.entites;


import coreapi.models.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "category_db")
public class Category {
    @Id
    private String id;
    private String categoryName;
    private String image;
    private Boolean active;
    private List<SubCategory> subCategories;


}
