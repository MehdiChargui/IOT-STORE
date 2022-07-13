package com.example.serviceadminread.repositories;

import com.example.serviceadminread.entites.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface AdminRepository extends MongoRepository<Admin,String> {
    Admin findByMail(String mail);
    Admin findByLoginAndPassword(String login,String password);

}
