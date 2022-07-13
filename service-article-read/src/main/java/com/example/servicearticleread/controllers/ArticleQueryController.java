package com.example.servicearticleread.controllers;

import com.example.servicearticleread.entites.Article;
import coreapi.querys.*;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/article/read")
public class ArticleQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allArticle")
    public List<Article> articleList() {
        List<Article> response =queryGateway.query(new GetAllArticleQuery(), ResponseTypes.multipleInstancesOf(Article.class)).join();
        return response;
    }

    @GetMapping("/getById/{id}")
    public Article getArticle(@PathVariable String id) {
        Article response =queryGateway.query(new GetArticleByIdQuery(id),ResponseTypes.instanceOf(Article.class)).join();
        return response;
    }

    @GetMapping("/allArticleByActive")
    public List<Article> articleByActiveList() {
        List<Article> response =queryGateway.query(new GetArticleByActiveQuery(true), ResponseTypes.multipleInstancesOf(Article.class)).join();
        return response;
    }

    @GetMapping("/allArticleByDiscount")
    public List<Article> articleByDiscountList() {
        List<Article> response =queryGateway.query(new GetArticleByDiscountQuery(true,true), ResponseTypes.multipleInstancesOf(Article.class)).join();
        return response;
    }

    @GetMapping("/allArticleByCategory/{category}")
    public List<Article> articleByCategoryList(@PathVariable String category) {
        List<Article> response =queryGateway.query(new GetArticleByCategoryQuery(true,category), ResponseTypes.multipleInstancesOf(Article.class)).join();
        return response;
    }

    @GetMapping("/allArticleBySubcategory/{subCategory}")
    public List<Article> articleBySubcategoryList(@PathVariable String subCategory) {
        List<Article> response =queryGateway.query(new GetArticleBySubcategoryQuery(true,subCategory), ResponseTypes.multipleInstancesOf(Article.class)).join();
        return response;
    }
}
