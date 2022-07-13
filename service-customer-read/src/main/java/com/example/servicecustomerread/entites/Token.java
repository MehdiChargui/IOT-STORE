package com.example.servicecustomerread.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;


@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "token_db")
public class Token {
    @Id
    private String id;
    private String _userId;
    private String token;
    @Indexed(expireAfterSeconds = 43200)
    private Date createdAt;

}
