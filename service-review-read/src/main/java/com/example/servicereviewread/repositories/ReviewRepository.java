package com.example.servicereviewread.repositories;

import com.example.servicereviewread.entites.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review,String> {
    List<Review> findByCustomer(String customer);

    List<Review> findByArticle(String article);
}
