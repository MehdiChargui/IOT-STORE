package coreapi.commands;

import coreapi.details.Details;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class CreateOrderCommand extends BaseCommand<String>{

    @Getter private String reference;
    @Getter private Date orderDate;
    @Getter private String customer;
    @Getter private String deliveryAddress;
    @Getter private String deliveryMethod;
    @Getter private String paymentMethod;
    @Getter private String status;
    @Getter private Boolean paymentStatus;
    @Getter private double total;
    @Getter private  double totalTTC;
    @Getter private List<Details> details;

    public CreateOrderCommand(String id, String reference, Date orderDate, String customer, String deliveryAddress, String deliveryMethod, String paymentMethod, String status, Boolean paymentStatus, double total, double totalTTC, List<Details> details) {
        super(id);
        this.reference = reference;
        this.orderDate = orderDate;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.deliveryMethod = deliveryMethod;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.total = total;
        this.totalTTC = totalTTC;
        this.details = details;
    }
}

