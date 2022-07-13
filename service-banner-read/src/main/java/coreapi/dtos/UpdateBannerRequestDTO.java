package coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBannerRequestDTO {
    private String id;
    private String title;
    private String description;
    private String location;
    private String type;
    private String link;
    private String image;
    private Boolean active;
}
