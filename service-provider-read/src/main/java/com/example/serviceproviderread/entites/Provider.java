package com.example.serviceproviderread.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;


@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "provider_db")
public class Provider {
    @Id
    private String id;
    private String designation;
    private String description;
    private String phoneNumber;
    private String mail;
    private String link;
    private String address;
    private String postalCode;
    private String city;
    private String country;
    private Date creationDate;
    private Boolean active;
    private Boolean deleted;

}

