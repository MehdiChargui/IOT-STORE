package com.example.servicecustomerread.entites;

import coreapi.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "customer_db")
public class Customer {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private List<Address> addresses;
    private String function;
    private String mail;
    private String login;
    private String password;
    private Date registrationDate;
    private Boolean mailVerified;
    private String accountType;
    private Boolean active;
    private Boolean deleted;

}
