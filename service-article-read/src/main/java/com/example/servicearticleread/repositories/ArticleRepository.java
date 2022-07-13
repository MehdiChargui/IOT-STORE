package com.example.servicearticleread.repositories;

import com.example.servicearticleread.entites.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public interface ArticleRepository extends MongoRepository<Article,String> {
    List<Article> findByActive(Boolean active);

    List<Article> findByActiveAndDiscountOnDiscount(Boolean active,Boolean discount);

    List<Article> findByActiveAndCategoriesCategory(Boolean active,String category);

    List<Article> findByActiveAndCategoriesSubCategory(Boolean active,String subCategory);
}
