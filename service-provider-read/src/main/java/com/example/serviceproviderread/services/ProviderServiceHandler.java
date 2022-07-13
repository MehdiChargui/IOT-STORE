package com.example.serviceproviderread.services;

import com.example.serviceproviderread.entites.Provider;
import com.example.serviceproviderread.repositories.ProviderRepository;
import coreapi.events.CreatedProviderEvent;
import coreapi.events.DeletedProviderEvent;
import coreapi.events.UpdatedProviderEvent;
import coreapi.querys.GetAllProviderQuery;
import coreapi.querys.GetProviderByIdQuery;
import coreapi.querys.GetProviderByMailQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProviderServiceHandler {
    @Autowired
    private ProviderRepository providerRepository;

    @EventHandler
    public void on(CreatedProviderEvent event){
        log.info("CreatedProviderEvent recived");
        Provider provider = new Provider(
                event.getId(),
                event.getDesignation(),
                event.getDescription(),
                event.getPhoneNumber(),
                event.getMail(),
                event.getLink(),
                event.getAddress(),
                event.getPostalCode(),
                event.getCity(),
                event.getCountry(),
                event.getCreationDate(),
                event.getActive(),
                event.getDeleted()
        );
        providerRepository.save(provider);
    }

    @EventHandler
    public void on(UpdatedProviderEvent event) {
        log.info("UpdatedProviderEvent recived");
        Provider provider = providerRepository.findById(event.getId()).get();
        provider.setDesignation(event.getDesignation());
        provider.setDescription(event.getDescription());
        provider.setPhoneNumber(event.getPhoneNumber());
        provider.setMail(event.getMail());
        provider.setLink(event.getLink());
        provider.setAddress(event.getAddress());
        provider.setPostalCode(event.getPostalCode());
        provider.setCity(event.getCity());
        provider.setCountry(event.getCountry());
        provider.setActive(event.getActive());
        provider.setDeleted(event.getDeleted());
        providerRepository.save(provider);
    }

    @EventHandler
    public void on(DeletedProviderEvent event) {
        log.info("DeletedProviderEvent recived");
        providerRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<Provider> on(GetAllProviderQuery query){
        return providerRepository.findAll();
    }

    @QueryHandler
    public Provider on(GetProviderByIdQuery query){
        return providerRepository.findById(query.getId()).get();
    }

    @QueryHandler
    public Provider on(GetProviderByMailQuery query){
        return providerRepository.findByMail(query.getMail());
    }

}
