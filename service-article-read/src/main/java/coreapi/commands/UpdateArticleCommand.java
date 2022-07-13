package coreapi.commands;


import coreapi.models.*;
import lombok.Getter;

import java.util.List;

public class UpdateArticleCommand extends BaseCommand<String>{

    @Getter private String title;
    @Getter private String description;
    @Getter private String reference;
    @Getter private String availability;
    @Getter private double price;
    @Getter private double tax;
    @Getter private int quantity;

    @Getter private Boolean active;
    @Getter private Boolean deleted;
    @Getter private Discount discount;
    @Getter private List<Image> images;
    @Getter private List<Video> videos;
    @Getter private List<Feature> features;
    @Getter private List<Category> categories;
    @Getter private List<PriceHistory> priceHistory;

    public UpdateArticleCommand(String id, String title, String description, String reference, String availability, double price, double tax, int quantity, Boolean active, Boolean deleted, Discount discount, List<Image> images, List<Video> videos, List<Feature> features, List<Category> categories, List<PriceHistory> priceHistory) {
        super(id);
        this.title = title;
        this.description = description;
        this.reference = reference;
        this.availability = availability;
        this.price = price;
        this.tax = tax;
        this.quantity = quantity;
        this.active = active;
        this.deleted = deleted;
        this.discount = discount;
        this.images = images;
        this.videos = videos;
        this.features = features;
        this.categories = categories;
        this.priceHistory = priceHistory;
    }
}

