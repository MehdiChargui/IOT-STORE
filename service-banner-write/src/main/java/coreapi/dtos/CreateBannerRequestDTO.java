package coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBannerRequestDTO {
    private String title;
    private String description;
    private String location;
    private String type;
    private String link;
    private String image;
}
