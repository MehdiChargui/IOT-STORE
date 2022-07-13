package com.example.serviceadminread.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "admin_db")
public class Admin {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String function;
    private String mail;
    private String login;
    private String password;
    private int role;
    private Boolean deleted;

}
