package com.example.servicereviewread.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;


@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "ReviewDB")
public class Review {
    @Id
    private String id;
    private String customer;
    private String article;
    private int note;
    private String comment;
    private Date creationDate;
    private Boolean active;
}

