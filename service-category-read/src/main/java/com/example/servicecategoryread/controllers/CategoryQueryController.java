package com.example.servicecategoryread.controllers;

import com.example.servicecategoryread.entites.Category;
import coreapi.querys.GetAllCategoryQuery;
import coreapi.querys.GetCategoryByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/category/read")
public class CategoryQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allCategory")
    public List<Category> categoryList() {
        List<Category> response =queryGateway.query(new GetAllCategoryQuery(), ResponseTypes.multipleInstancesOf(Category.class)).join();
        return response;
    }

    @GetMapping("/getById/{id}")
    public Category getBanner(@PathVariable String id) {
        Category response =queryGateway.query(new GetCategoryByIdQuery(id),ResponseTypes.instanceOf(Category.class)).join();
        return response;
    }

}
