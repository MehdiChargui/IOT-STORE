package com.example.servicebannerread.services;

import com.example.servicebannerread.entites.Banner;
import com.example.servicebannerread.repositories.BannerRepository;
import coreapi.events.CreatedBannerEvent;
import coreapi.events.DeletedBannerEvent;
import coreapi.events.UpdatedBannerEvent;
import coreapi.querys.GetAllBannerQuery;
import coreapi.querys.GetBannerByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BannerServiceHandler {
    @Autowired
    private BannerRepository bannerRepository;

    @EventHandler
    public void on(CreatedBannerEvent event){
        log.info("CreatedBannerEvent recived");
        Banner banner = new Banner(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getLocation(),
                event.getType(),
                event.getLink(),
                event.getImage(),
                event.getCreationDate(),
                event.getActive()
        );
        bannerRepository.save(banner);
    }

    @EventHandler
    public void on(UpdatedBannerEvent event) {
        log.info("UpdatedBannerEvent recived");
        Banner banner = bannerRepository.findById(event.getId()).get();
        banner.setTitle(event.getTitle());
        banner.setDescription(event.getDescription());
        banner.setLocation(event.getLocation());
        banner.setType(event.getType());
        banner.setLink(event.getLink());
        banner.setImage(event.getImage());
        banner.setActive(event.getActive());
        bannerRepository.save(banner);
    }

    @EventHandler
    public void on(DeletedBannerEvent event) {
        log.info("DeletedBannerEvent recived");
        bannerRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Banner> on(GetAllBannerQuery query){
        return bannerRepository.findAll();
    }

    @QueryHandler
    public Banner on(GetBannerByIdQuery query){
        return bannerRepository.findById(query.getId()).get();
    }

}
