package com.example.servicecategoryread.repositories;

import com.example.servicecategoryread.entites.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category,String> {
}
