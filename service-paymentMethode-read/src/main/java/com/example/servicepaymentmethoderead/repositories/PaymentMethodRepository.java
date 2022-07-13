package com.example.servicepaymentmethoderead.repositories;



import com.example.servicepaymentmethoderead.entites.PaymentMethod;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentMethodRepository extends MongoRepository<PaymentMethod,String> {
}
