package com.example.serviceorderread.entites;


import coreapi.details.Details;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "order_db")
public class Order {
    @Id
    private String id;

    private String reference;
    private Date orderDate;
    private String customer;
    private String deliveryAddress;
    private String deliveryMethod;
    private String paymentMethod;
    private String status;
    private Boolean paymentStatus;
    private double total;
    private  double totalTTC;
    private List<Details> details;


}
