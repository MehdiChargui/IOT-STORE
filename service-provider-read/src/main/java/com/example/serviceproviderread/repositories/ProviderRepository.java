package com.example.serviceproviderread.repositories;

import com.example.serviceproviderread.entites.Provider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;


@Controller
public interface ProviderRepository extends MongoRepository<Provider,String> {
    Provider findByMail(String mail);
}
