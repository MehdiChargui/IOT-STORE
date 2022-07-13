package com.example.servicereviewwrite.controllers;

import coreapi.commands.CreateReviewCommand;
import coreapi.commands.DeleteReviewCommand;
import coreapi.commands.UpdateReviewCommand;
import coreapi.dtos.CreateReviewRequestDTO;
import coreapi.dtos.UpdateReviewRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(path = "/review/write")
public class ReviewCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createReview(@RequestBody CreateReviewRequestDTO request) {
        if(Objects.isNull(request))
            return "Review can not be empty";
        try{
            CreateReviewCommand createReviewCommand = new CreateReviewCommand(
                    UUID.randomUUID().toString(),
                    request.getCustomer(),
                    request.getArticle(),
                    request.getNote(),
                    request.getComment(),
                    new Date(),
                    true
            );
            commandGateway.send(createReviewCommand);
            return createReviewCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the review.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateReview(@RequestBody UpdateReviewRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Review can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateReviewCommand updateReviewCommand = new UpdateReviewCommand(
                        request.getId(),
                        request.getCustomer(),
                        request.getArticle(),
                        request.getNote(),
                        request.getComment(),
                        request.getActive()
                );
                commandGateway.send(updateReviewCommand);
                return updateReviewCommand;
            }else
                return "Review not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating review with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public Object deleteReview(@PathVariable String idReview) {
        if(idReview.isEmpty())
            return "ID Review can not be empty";
        try {
            DeleteReviewCommand deleteReviewCommand = new DeleteReviewCommand(idReview);
            commandGateway.send(deleteReviewCommand);
            return deleteReviewCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete review with id " + idReview;
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity=new ResponseEntity<String>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}