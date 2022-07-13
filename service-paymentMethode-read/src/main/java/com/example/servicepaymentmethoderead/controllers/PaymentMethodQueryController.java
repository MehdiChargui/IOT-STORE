package com.example.servicepaymentmethoderead.controllers;


import com.example.servicepaymentmethoderead.entites.PaymentMethod;
import coreapi.querys.GetAllPaymentMethodQuery;
import coreapi.querys.GetPaymentMethodByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/paymentMethod/read")
public class PaymentMethodQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allPaymentMethods")
    public List<PaymentMethod> PaymentMethodList() {
        List<PaymentMethod> response =queryGateway.query(new GetAllPaymentMethodQuery(), ResponseTypes.multipleInstancesOf(PaymentMethod.class)).join();
        return response;
    }

    @GetMapping("/getById/{id}")
    public PaymentMethod getPaymentMethod(@PathVariable String id) {
        PaymentMethod response =queryGateway.query(new GetPaymentMethodByIdQuery(id),ResponseTypes.instanceOf(PaymentMethod.class)).join();
        return response;
    }

}
