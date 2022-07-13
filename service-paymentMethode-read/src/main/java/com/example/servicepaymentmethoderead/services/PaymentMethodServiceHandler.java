package com.example.servicepaymentmethoderead.services;
import com.example.servicepaymentmethoderead.entites.PaymentMethod;
import com.example.servicepaymentmethoderead.repositories.PaymentMethodRepository;
import coreapi.events.CreatedPaymentMethodEvent;
import coreapi.events.DeletedPaymentMethodEvent;
import coreapi.events.UpdatedPaymentMethodEvent;
import coreapi.querys.GetAllPaymentMethodQuery;
import coreapi.querys.GetPaymentMethodByIdQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PaymentMethodServiceHandler {
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @EventHandler
    public void on(CreatedPaymentMethodEvent event){
        log.info("CreatedPaymentMethodEvent recived");
        PaymentMethod paymentMethod = new PaymentMethod(
                event.getId(),
                event.getName(),
                event.getActive(),
                event.getDeleted()
        );
        paymentMethodRepository.save(paymentMethod);
    }

    @EventHandler
    public void on(UpdatedPaymentMethodEvent event) {
        log.info("UpdatedPaymentMethodEvent recived");
        PaymentMethod paymentMethod = paymentMethodRepository.findById(event.getId()).get();
        paymentMethod.setName(event.getName());
        paymentMethod.setActive(event.getActive());
        paymentMethod.setDeleted(event.getDeleted());
        paymentMethodRepository.save(paymentMethod);
    }

    @EventHandler
    public void on(DeletedPaymentMethodEvent event) {
        log.info("DeletedPaymentMethodEvent recived");
        paymentMethodRepository.deleteById(event.getId());
    }

    @QueryHandler
    public List<PaymentMethod> on(GetAllPaymentMethodQuery query){
        return paymentMethodRepository.findAll();
    }

    @QueryHandler
    public PaymentMethod on(GetPaymentMethodByIdQuery query){
        return paymentMethodRepository.findById(query.getId()).get();
    }

}
