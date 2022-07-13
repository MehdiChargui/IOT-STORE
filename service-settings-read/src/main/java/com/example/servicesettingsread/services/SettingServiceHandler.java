package com.example.servicesettingsread.services;

import com.example.servicesettingsread.entites.Setting;
import com.example.servicesettingsread.repositories.SettingRepository;
import coreapi.events.CreatedSettingEvent;
import coreapi.events.DeletedSettingEvent;
import coreapi.events.UpdatedSettingEvent;
import coreapi.querys.GetAllSettingQuery;
import coreapi.querys.GetSettingByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class SettingServiceHandler {
    @Autowired
    private SettingRepository settingRepository;



    @EventHandler
    public void on(CreatedSettingEvent event){
        log.info("CreatedSettingEvent recived");
        Setting setting = new Setting(
                event.getId(),
                event.getDescription(),
                event.getMailList(),
                event.getLinks()
        );
        settingRepository.save(setting);
    }

    @EventHandler
    public void on(UpdatedSettingEvent event) {
        log.info("UpdatedSettingEvent recived");
        Setting setting = settingRepository.findById(event.getId()).get();
        setting.setDescription(event.getDescription());
        setting.setMailList(event.getMailList());
        setting.setLinks(event.getLinks());
        settingRepository.save(setting);
    }

    @EventHandler
    public void on(DeletedSettingEvent event) {
        log.info("DeletedSettingEvent recived");
        settingRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Setting> on(GetAllSettingQuery query){
        return settingRepository.findAll();
    }

    @QueryHandler
    public Setting on(GetSettingByIdQuery query){
        return settingRepository.findById(query.getId()).get();
    }


}
