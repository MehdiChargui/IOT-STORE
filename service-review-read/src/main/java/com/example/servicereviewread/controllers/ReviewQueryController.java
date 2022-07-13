package com.example.servicereviewread.controllers;

import com.example.servicereviewread.entites.Review;
import coreapi.querys.GetAllReviewQuery;
import coreapi.querys.GetReviewByArticleQuery;
import coreapi.querys.GetReviewByCustomerQuery;
import coreapi.querys.GetReviewByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/review/read")
public class ReviewQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allReview")
    public List<Review> ReviewList() {
        List<Review> response =queryGateway.query(new GetAllReviewQuery(), ResponseTypes.multipleInstancesOf(Review.class)).join();
        return response;
    }

    @GetMapping("/getById/{id}")
    public Review getReview(@PathVariable String id) {
        Review response =queryGateway.query(new GetReviewByIdQuery(id),ResponseTypes.instanceOf(Review.class)).join();
        return response;
    }

    @GetMapping("/getByCustomer/{customer}")
    public List<Review> getReviewByCustomer(@PathVariable String customer) {
        List<Review> response =queryGateway.query(new GetReviewByCustomerQuery(customer),ResponseTypes.multipleInstancesOf(Review.class)).join();
        return response;
    }

    @GetMapping("/getByArticle/{article}")
    public List<Review> getReviewByArticle(@PathVariable String article) {
        List<Review> response =queryGateway.query(new GetReviewByArticleQuery(article),ResponseTypes.multipleInstancesOf(Review.class)).join();
        return response;
    }
}
