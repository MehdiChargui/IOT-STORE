package coreapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDeliveryMethodRequestDTO {
    private String id;
    private String name;
    private Boolean active;
    private Boolean deleted;
}

