package com.example.servicesettingsread.repositories;


import com.example.servicesettingsread.entites.Setting;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SettingRepository extends MongoRepository<Setting,String> { }
