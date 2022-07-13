package com.example.serviceproviderread.controllers;

import com.example.serviceproviderread.entites.Provider;
import coreapi.querys.GetAllProviderQuery;
import coreapi.querys.GetProviderByIdQuery;
import coreapi.querys.GetProviderByMailQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/provider/read")
public class ProviderQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allProviders")
    public Object ProviderList() {
        try{
            List<Provider> response =queryGateway.query(new GetAllProviderQuery(), ResponseTypes.multipleInstancesOf(Provider.class)).join();
            return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the provider.";
        }
    }

    @GetMapping("/getById/{id}")
    public Object getProvider(@PathVariable String id) {
        try {
            Provider response =queryGateway.query(new GetProviderByIdQuery(id),ResponseTypes.instanceOf(Provider.class)).join();
            if(Objects.isNull(response))
                return "Admin not found with id " + id;
            else
                return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the provider.";
        }
    }

    @GetMapping("/getByMail/{mail}")
    public Object getProviderByMail(@PathVariable String mail) {
        try{
            Provider response =queryGateway.query(new GetProviderByMailQuery(mail),ResponseTypes.instanceOf(Provider.class)).join();
            if(Objects.isNull(response))
                return "Provider not found with mail " + mail;
            else
                return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the provider.";
        }
    }

}
