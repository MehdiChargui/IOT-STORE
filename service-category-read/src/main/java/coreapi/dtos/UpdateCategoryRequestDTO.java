package coreapi.dtos;


import coreapi.models.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequestDTO {
    private String id;
    private String categoryName;
    private String image;
    private Boolean active;
    private List<SubCategory> subCategories;
}

