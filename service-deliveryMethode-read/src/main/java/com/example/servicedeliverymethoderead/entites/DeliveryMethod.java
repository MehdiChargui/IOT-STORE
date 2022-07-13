package com.example.servicedeliverymethoderead.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "deliverymethods")
public class DeliveryMethod {
    @Id
    private String id;
    private String name;
    private Boolean active;
    private Boolean deleted;

}

