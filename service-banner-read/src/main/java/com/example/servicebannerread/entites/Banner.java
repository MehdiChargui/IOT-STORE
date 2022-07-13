package com.example.servicebannerread.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "banner_db")
public class Banner {
    @Id
    private String id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String link;
    private String image;
    private Date creationDate;
    private Boolean active;

}

