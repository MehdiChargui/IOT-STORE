package com.example.servicecustomerread.repositories;


import com.example.servicecustomerread.entites.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface CustomerRepository extends MongoRepository<Customer,String> {
    Customer findByMail(String mail);
    Customer findByLoginAndPassword(String login,String password);

}
