package com.example.servicearticlewrite.controllers;

import coreapi.commands.CreateArticleCommand;
import coreapi.commands.DeleteArticleCommand;
import coreapi.commands.UpdateArticleCommand;
import coreapi.dtos.CreateArticleRequestDTO;
import coreapi.dtos.UpdateArticleRequestDTO;
import coreapi.models.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/article/write")
public class ArticleCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createArticle(@RequestBody CreateArticleRequestDTO request) {
        if(Objects.isNull(request))
            return "Customer can not be empty";
        try{
            CreateArticleCommand createArticleCommand = new CreateArticleCommand(
                    UUID.randomUUID().toString(),
                    request.getTitle(),
                    request.getDescription(),
                    request.getReference(),
                    request.getAvailability(),
                    Optional.ofNullable(request.getPrice()).orElse(0.0),
                    Optional.ofNullable(request.getTax()).orElse(0.18),
                    request.getQuantity(),
                    new Date(),
                    true,
                    false,
                    Optional.ofNullable(request.getDiscount()).orElse(new Discount(false,0)),
                    mapIDsToImages(request.getImages()),
                    mapIDsToVideos(request.getVideos()),
                    mapIDsToFeature(request.getFeatures()),
                    mapIDsToCategory(request.getCategories()),
                    mapIDsToPriceHistory(request.getPriceHistory())
            );
            commandGateway.send(createArticleCommand);
            return createArticleCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the customer.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateArticle(@RequestBody UpdateArticleRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Article can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateArticleCommand updateArticleCommand = new UpdateArticleCommand(
                        request.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getReference(),
                        request.getAvailability(),
                        request.getPrice(),
                        request.getTax(),
                        request.getQuantity(),
                        request.getActive(),
                        request.getDeleted(),
                        request.getDiscount(),
                        mapIDsToImages(request.getImages()),
                        mapIDsToVideos(request.getVideos()),
                        mapIDsToFeature(request.getFeatures()),
                        mapIDsToCategory(request.getCategories()),
                        mapIDsToPriceHistory(request.getPriceHistory())
                );
                commandGateway.send(updateArticleCommand);
                return updateArticleCommand;
            }else
                return "Article not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating article with id " + id;
        }
    }

    @DeleteMapping(path = "/delete/{idArticle}")
    public Object deleteArticle(@PathVariable String idArticle) {
        if(idArticle.isEmpty())
            return "ID Article can not be empty";
        try {
            DeleteArticleCommand deleteArticleCommand = new DeleteArticleCommand(idArticle);
            commandGateway.send(deleteArticleCommand);
            return deleteArticleCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete article with id " + idArticle;
        }
    }




    private List<Image> mapIDsToImages(List<Image> images) {
        if (Objects.isNull(images))
            return null;
        else {
            return images.stream().map(sc -> new Image(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getPath()
            )).collect(Collectors.toList());
        }
    }

    private List<Video> mapIDsToVideos(List<Video> videos) {
        if (Objects.isNull(videos))
            return null;
        else {
            return videos.stream().map(sc -> new Video(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getPath()
            )).collect(Collectors.toList());
        }
    }

    private List<Feature> mapIDsToFeature(List<Feature> features) {
        if (Objects.isNull(features))
            return null;
        else {
            return features.stream().map(sc -> new Feature(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getName(),
                    sc.getValue()
            )).collect(Collectors.toList());
        }
    }

    private List<Category> mapIDsToCategory(List<Category> categories) {
        if (Objects.isNull(categories))
            return null;
        else {
            return categories.stream().map(sc -> new Category(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getCategory(),
                    sc.getSubCategory()
            )).collect(Collectors.toList());
        }
    }
    private List<PriceHistory> mapIDsToPriceHistory(List<PriceHistory> priceHistories) {
        if (Objects.isNull(priceHistories))
            return null;
        else {
            return priceHistories.stream().map(sc -> new PriceHistory(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getPrice(),
                    new Date()
            )).collect(Collectors.toList());
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