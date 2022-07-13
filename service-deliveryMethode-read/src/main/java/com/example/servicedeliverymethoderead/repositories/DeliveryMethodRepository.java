package com.example.servicedeliverymethoderead.repositories;

import com.example.servicedeliverymethoderead.entites.DeliveryMethod;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryMethodRepository extends MongoRepository<DeliveryMethod,String> {
}
