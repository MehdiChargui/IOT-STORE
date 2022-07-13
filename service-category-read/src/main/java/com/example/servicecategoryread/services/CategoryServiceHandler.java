package com.example.servicecategoryread.services;

import com.example.servicecategoryread.entites.Category;
import com.example.servicecategoryread.repositories.CategoryRepository;
import coreapi.events.CreatedCategoryEvent;
import coreapi.events.DeletedCategoryEvent;
import coreapi.events.UpdatedCategoryEvent;
import coreapi.querys.GetAllCategoryQuery;
import coreapi.querys.GetCategoryByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class CategoryServiceHandler {
    @Autowired
    private CategoryRepository categoryRepository;



    @EventHandler
    public void on(CreatedCategoryEvent event){
        log.info("CreatedCategoryEvent recived");
        Category category = new Category(
                event.getId(),
                event.getCategoryName(),
                event.getImage(),
                event.getActive(),
                event.getSubCategories()
        );
        categoryRepository.save(category);
    }

    @EventHandler
    public void on(UpdatedCategoryEvent event) {
        log.info("UpdatedCategoryEvent recived");
        Category category = categoryRepository.findById(event.getId()).get();
        category.setCategoryName(event.getCategoryName());
        category.setImage(event.getImage());
        category.setActive(event.getActive());
        category.setSubCategories(event.getSubCategories());
        categoryRepository.save(category);
    }

    @EventHandler
    public void on(DeletedCategoryEvent event) {
        log.info("DeletedCategoryEvent recived");
        categoryRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Category> on(GetAllCategoryQuery query){
        return categoryRepository.findAll();
    }

    @QueryHandler
    public Category on(GetCategoryByIdQuery query){
        return categoryRepository.findById(query.getId()).get();
    }

}
