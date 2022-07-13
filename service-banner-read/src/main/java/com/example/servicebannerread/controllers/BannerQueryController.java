package com.example.servicebannerread.controllers;

import com.example.servicebannerread.entites.Banner;
import coreapi.querys.GetAllBannerQuery;
import coreapi.querys.GetBannerByIdQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/banner/read")
public class BannerQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/allBanners")
    public List<Banner> bannerList() {
        List<Banner> response =queryGateway.query(new GetAllBannerQuery(), ResponseTypes.multipleInstancesOf(Banner.class)).join();
        return response;
    }

    @GetMapping("/getById/{id}")
    public Banner getBanner(@PathVariable String id) {
        Banner response =queryGateway.query(new GetBannerByIdQuery(id),ResponseTypes.instanceOf(Banner.class)).join();
        return response;
    }

}
