package com.example.servicebannerread.repositories;

import com.example.servicebannerread.entites.Banner;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannerRepository extends MongoRepository<Banner,String> {
}
