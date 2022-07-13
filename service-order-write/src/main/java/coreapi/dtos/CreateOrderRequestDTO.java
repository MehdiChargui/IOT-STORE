package coreapi.dtos;

import coreapi.details.Details;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequestDTO {
    private String reference;
    private Date orderDate;
    private String customer;
    private String deliveryAddress;
    private String deliveryMethod;
    private String paymentMethod;
    private String status;
    private Boolean paymentStatus;
    private double total;
    private  double totalTTC;
    private List<Details> details;
}

