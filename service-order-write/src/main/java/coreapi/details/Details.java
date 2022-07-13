package coreapi.details;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    private String id;
    private String article;
    private int quantity;
    private double unitPrice;

}