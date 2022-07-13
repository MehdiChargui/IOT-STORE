package com.example.serviceadminread.services;

import com.example.serviceadminread.entites.Admin;
import com.example.serviceadminread.repositories.AdminRepository;
import coreapi.events.CreatedAdminEvent;
import coreapi.events.DeletedAdminEvent;
import coreapi.events.UpdatedAdminEvent;
import coreapi.querys.GetAdminByIdQuery;
import coreapi.querys.GetAdminByLoginAndPasswordQuery;
import coreapi.querys.GetAdminByMailQuery;
import coreapi.querys.GetAllAdminQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class AdminServiceHandler {
    @Autowired
    private AdminRepository adminRepository;



    @EventHandler
    public void on(CreatedAdminEvent event){
        log.info("CreatedAdminEvent recived");
        Admin admin = new Admin(
                event.getId(),
                event.getFirstname(),
                event.getLastname(),
                event.getAddress(),
                event.getPostalCode(),
                event.getPhoneNumber(),
                event.getFunction(),
                event.getMail(),
                event.getLogin(),
                event.getPassword(),
                event.getRole(),
                event.getDeleted()
        );
        adminRepository.save(admin);
    }

    @EventHandler
    public void on(UpdatedAdminEvent event) {
        log.info("UpdatedAdminEvent recived");
        Admin admin = adminRepository.findById(event.getId()).get();
        admin.setFirstname(event.getFirstname());
        admin.setLastname(event.getLastname());
        admin.setAddress(event.getAddress());
        admin.setPostalCode(event.getPostalCode());
        admin.setPhoneNumber(event.getPhoneNumber());
        admin.setFunction(event.getFunction());
        admin.setMail(event.getMail());
        admin.setLogin(event.getLogin());
        admin.setPassword(event.getPassword());
        admin.setRole(event.getRole());
        admin.setDeleted(event.getDeleted());
        adminRepository.save(admin);
    }

    @EventHandler
    public void on(DeletedAdminEvent event) {
        log.info("DeletedAdminEvent recived");
        adminRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Admin> on(GetAllAdminQuery query){
        return adminRepository.findAll();
    }

    @QueryHandler
    public Admin on(GetAdminByIdQuery query){
        return adminRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public Admin on(GetAdminByMailQuery query){
        return adminRepository.findByMail(query.getMail());
    }

    @QueryHandler
    public Admin on(GetAdminByLoginAndPasswordQuery query){
        return adminRepository.findByLoginAndPassword(query.getLogin(), query.getPassword());
    }


}
