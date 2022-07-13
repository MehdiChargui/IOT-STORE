package com.example.servicesettingsread.entites;

import coreapi.models.Links;
import coreapi.models.MailList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Document(collection = "setting_db")
public class Setting {
    @Id
    private String id;
    private String description;
    private List<MailList> mailList;
    private List<Links> links;
}
