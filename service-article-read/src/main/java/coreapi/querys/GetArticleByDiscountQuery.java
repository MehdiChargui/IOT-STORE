package coreapi.querys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleByDiscountQuery {
    private Boolean active;
    private Boolean discount;
}
