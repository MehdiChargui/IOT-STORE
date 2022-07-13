package com.example.serviceadminread.controllers;

import com.example.serviceadminread.entites.Admin;
import coreapi.querys.GetAdminByIdQuery;
import coreapi.querys.GetAdminByLoginAndPasswordQuery;
import coreapi.querys.GetAdminByMailQuery;
import coreapi.querys.GetAllAdminQuery;
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
@RequestMapping(path = "/admin/read")
public class AdminQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allAdmins")
    public Object AdminList() {
        try {
            List<Admin> response =queryGateway.query(new GetAllAdminQuery(), ResponseTypes.multipleInstancesOf(Admin.class)).join();
            return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the admin.";
        }
    }

    @GetMapping("/getById/{id}")
    public Object getAdmin(@PathVariable String id) {
        try {
            Admin response =queryGateway.query(new GetAdminByIdQuery(id),ResponseTypes.instanceOf(Admin.class)).join();
            if(Objects.isNull(response))
                return "Admin not found with id " + id;
            else
                return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the admin.";
        }
    }

    @GetMapping("/getByMail/{mail}")
    public Object getAdminByMail(@PathVariable String mail) {
        try {
            Admin response = queryGateway.query(new GetAdminByMailQuery(mail), ResponseTypes.instanceOf(Admin.class)).join();
            return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the admin.";
        }
    }

    @GetMapping("/connect/{login}/{password}")
    public Object getAdminByMailAndPassword(@PathVariable String login,@PathVariable String password) {
        try {
            Admin response =queryGateway.query(new GetAdminByLoginAndPasswordQuery(login,password),ResponseTypes.instanceOf(Admin.class)).join();
            return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the admin.";
        }
    }

}
