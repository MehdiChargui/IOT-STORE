package coreapi.querys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleBySubcategoryQuery {
    private Boolean active;
    private String subCategory;
}
