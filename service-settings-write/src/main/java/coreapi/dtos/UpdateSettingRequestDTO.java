package coreapi.dtos;

import coreapi.models.Links;
import coreapi.models.MailList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSettingRequestDTO {
    private String id;
    private String description;
    private List<MailList> mailList;
    private List<Links> links;
}

