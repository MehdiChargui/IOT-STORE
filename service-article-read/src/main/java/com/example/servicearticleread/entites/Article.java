package com.example.servicearticleread.entites;

import coreapi.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "article_db")
public class Article {
    @Id
    private String id;
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


}
