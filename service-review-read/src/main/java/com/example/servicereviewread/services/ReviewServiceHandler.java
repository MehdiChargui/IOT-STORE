package com.example.servicereviewread.services;

import com.example.servicereviewread.entites.Review;
import com.example.servicereviewread.repositories.ReviewRepository;
import coreapi.events.CreatedReviewEvent;
import coreapi.events.DeletedReviewEvent;
import coreapi.events.UpdatedReviewEvent;
import coreapi.querys.GetAllReviewQuery;
import coreapi.querys.GetReviewByArticleQuery;
import coreapi.querys.GetReviewByCustomerQuery;
import coreapi.querys.GetReviewByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewServiceHandler {
    @Autowired
    private ReviewRepository reviewRepository;

    @EventHandler
    public void on(CreatedReviewEvent event){
        log.info("CreatedReviewEvent recived");
        Review review = new Review(
                event.getId(),
                event.getCustomer(),
                event.getArticle(),
                event.getNote(),
                event.getComment(),
                event.getCreationDate(),
                event.getActive()
        );
        reviewRepository.save(review);
    }

    @EventHandler
    public void on(UpdatedReviewEvent event) {
        log.info("UpdatedReviewEvent recived");
        Review review = reviewRepository.findById(event.getId()).get();
        review.setCustomer(event.getCustomer());
        review.setArticle(event.getArticle());
        review.setNote(event.getNote());
        review.setComment(event.getComment());
        review.setActive(event.getActive());
    }

    @EventHandler
    public void on(DeletedReviewEvent event) {
        log.info("DeletedReviewEvent recived");
        reviewRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Review> on(GetAllReviewQuery query){
        return reviewRepository.findAll();
    }

    @QueryHandler
    public Review on(GetReviewByIdQuery query){
        return reviewRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public List<Review> on(GetReviewByCustomerQuery query){
        return reviewRepository.findByCustomer(query.getCustomer());
    }

    @QueryHandler
    public List<Review> on(GetReviewByArticleQuery query){
        return reviewRepository.findByArticle(query.getArticle());
    }

}
