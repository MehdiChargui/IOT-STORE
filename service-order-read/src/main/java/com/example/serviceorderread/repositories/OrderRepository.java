package com.example.serviceorderread.repositories;

import com.example.serviceorderread.entites.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findByCustomer(String customer);

    void deleteOrdersByCustomer(String customer);
}
