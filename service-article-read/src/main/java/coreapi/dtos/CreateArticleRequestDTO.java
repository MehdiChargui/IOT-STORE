package coreapi.dtos;


import coreapi.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateArticleRequestDTO {
    private String title;
    private String description;
    private String reference;
    private String availability;
    private double price;
    private double tax;
    private int quantity;
    private Discount discount;
    private List<Image> images;
    private List<Video> videos;
    private List<Feature> features;
    private List<Category> categories;
    private List<PriceHistory> priceHistory;

}

