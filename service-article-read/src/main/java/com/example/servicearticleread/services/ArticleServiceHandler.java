package com.example.servicearticleread.services;

import com.example.servicearticleread.entites.Article;
import com.example.servicearticleread.repositories.ArticleRepository;
import coreapi.events.CreatedArticleEvent;
import coreapi.events.DeletedArticleEvent;
import coreapi.events.UpdatedArticleEvent;
import coreapi.querys.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ArticleServiceHandler {
    @Autowired
    private ArticleRepository articleRepository;



    @EventHandler
    public void on(CreatedArticleEvent event){
        log.info("CreatedArticleEvent recived");
        Article article = new Article(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getReference(),
                event.getAvailability(),
                event.getPrice(),
                event.getTax(),
                event.getQuantity(),
                event.getCreationDate(),
                event.getActive(),
                event.getDeleted(),
                event.getDiscount(),
                event.getImages(),
                event.getVideos(),
                event.getFeatures(),
                event.getCategories(),
                event.getPriceHistory()
        );
        articleRepository.save(article);
    }

    @EventHandler
    public void on(UpdatedArticleEvent event) {
        log.info("UpdatedArticleEvent recived");
        Article article = articleRepository.findById(event.getId()).get();
        article.setTitle(event.getTitle());
        article.setDescription(event.getDescription());
        article.setReference(event.getReference());
        article.setAvailability(event.getAvailability());
        article.setPrice(event.getPrice());
        article.setTax(event.getTax());
        article.setQuantity(event.getQuantity());
        article.setActive(event.getActive());
        article.setDeleted(event.getDeleted());
        article.setDiscount(event.getDiscount());
        article.setImages(event.getImages());
        article.setVideos(event.getVideos());
        article.setFeatures(event.getFeatures());
        article.setCategories(event.getCategories());
        article.setPriceHistory(event.getPriceHistory());
        articleRepository.save(article);
    }

    @EventHandler
    public void on(DeletedArticleEvent event) {
        log.info("DeletedArticleEvent recived");
        articleRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Article> on(GetAllArticleQuery query){
        return articleRepository.findAll();
    }

    @QueryHandler
    public Article on(GetArticleByIdQuery query){
        return articleRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public List<Article> on(GetArticleByActiveQuery query){
        return articleRepository.findByActive(query.getActive());
    }

    @QueryHandler
    public List<Article> on(GetArticleByDiscountQuery query){
        return articleRepository.findByActiveAndDiscountOnDiscount(query.getActive(),query.getDiscount());
    }

    @QueryHandler
    public List<Article> on(GetArticleByCategoryQuery query){
        return articleRepository.findByActiveAndCategoriesCategory(query.getActive(),query.getCategory());
    }

    @QueryHandler
    public List<Article> on(GetArticleBySubcategoryQuery query){
        return articleRepository.findByActiveAndCategoriesSubCategory(query.getActive(),query.getSubCategory());
    }
}
