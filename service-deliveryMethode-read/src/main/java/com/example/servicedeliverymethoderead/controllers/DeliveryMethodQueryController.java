package com.example.servicedeliverymethoderead.controllers;

import com.example.servicedeliverymethoderead.entites.DeliveryMethod;
import coreapi.querys.GetAllDeliveryMethodQuery;
import coreapi.querys.GetDeliveryMethodByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/deliveryMethod/read")
public class DeliveryMethodQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allDeliveryMethods")
    public List<DeliveryMethod> DeliveryMethodList() {
        List<DeliveryMethod> response =queryGateway.query(new GetAllDeliveryMethodQuery(), ResponseTypes.multipleInstancesOf(DeliveryMethod.class)).join();
        return response;
    }

    @GetMapping("/getById/{id}")
    public DeliveryMethod getDeliveryMethod(@PathVariable String id) {
        DeliveryMethod response =queryGateway.query(new GetDeliveryMethodByIdQuery(id),ResponseTypes.instanceOf(DeliveryMethod.class)).join();
        return response;
    }

}
