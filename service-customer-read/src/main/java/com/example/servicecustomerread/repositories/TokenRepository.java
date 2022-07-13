package com.example.servicecustomerread.repositories;

import com.example.servicecustomerread.entites.Token;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface TokenRepository extends MongoRepository<Token,String> {
    Token findByToken(String token);
}
