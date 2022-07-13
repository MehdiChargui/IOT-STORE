package com.example.servicesettingsread.controllers;


import com.example.servicesettingsread.entites.Setting;
import coreapi.querys.GetAllSettingQuery;
import coreapi.querys.GetSettingByIdQuery;
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
@RequestMapping(path = "/setting/read")
public class SettingQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allSettings")
    public List<Setting> SettingList() {
            List<Setting> response =queryGateway.query(new GetAllSettingQuery(), ResponseTypes.multipleInstancesOf(Setting.class)).join();
            return response;

    }

    @GetMapping("/getById/{id}")
    public Object getSetting(@PathVariable String id) {
        try {
            Setting response =queryGateway.query(new GetSettingByIdQuery(id),ResponseTypes.instanceOf(Setting.class)).join();
            if(Objects.isNull(response))
                return "Setting not found with id " + id;
            else
                return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the setting.";
        }
    }

}
