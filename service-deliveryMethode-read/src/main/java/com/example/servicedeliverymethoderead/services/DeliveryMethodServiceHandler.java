package com.example.servicedeliverymethoderead.services;
import com.example.servicedeliverymethoderead.entites.DeliveryMethod;
import com.example.servicedeliverymethoderead.repositories.DeliveryMethodRepository;
import coreapi.events.CreatedDeliveryMethodEvent;
import coreapi.events.DeletedDeliveryMethodEvent;
import coreapi.events.UpdatedDeliveryMethodEvent;
import coreapi.querys.GetAllDeliveryMethodQuery;
import coreapi.querys.GetDeliveryMethodByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeliveryMethodServiceHandler {
    @Autowired
    private DeliveryMethodRepository deliveryMethodRepository;

    @EventHandler
    public void on(CreatedDeliveryMethodEvent event){
        log.info("CreatedDeliveryMethodEvent recived");
        DeliveryMethod deliveryMethod = new DeliveryMethod(
                event.getId(),
                event.getName(),
                event.getActive(),
                event.getDeleted()
        );
        deliveryMethodRepository.save(deliveryMethod);
    }

    @EventHandler
    public void on(UpdatedDeliveryMethodEvent event) {
        log.info("UpdatedDeliveryMethodEvent recived");
        DeliveryMethod deliveryMethod = deliveryMethodRepository.findById(event.getId()).get();
        deliveryMethod.setName(event.getName());
        deliveryMethod.setActive(event.getActive());
        deliveryMethod.setDeleted(event.getDeleted());
        deliveryMethodRepository.save(deliveryMethod);
    }

    @EventHandler
    public void on(DeletedDeliveryMethodEvent event) {
        log.info("DeletedDeliveryMethodEvent recived");
        deliveryMethodRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<DeliveryMethod> on(GetAllDeliveryMethodQuery query){
        return deliveryMethodRepository.findAll();
    }

    @QueryHandler
    public DeliveryMethod on(GetDeliveryMethodByIdQuery query){
        return deliveryMethodRepository.findById(query.getId()).get();
    }

}
