package com.example.servicecustomerread.services;

import com.example.servicecustomerread.entites.Token;
import com.example.servicecustomerread.repositories.TokenRepository;
import coreapi.events.CreatedTokenEvent;
import coreapi.querys.GetTokenByTokenQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class  TokenServiceHandler {
    @Autowired
    private TokenRepository tokenRepository;

    @EventHandler
    public void on(CreatedTokenEvent event){
        log.info("CreatedTokenEvent recived");
        Token token = new Token(
                event.getId(),
                event.get_userId(),
                event.getToken(),
                event.getCreatedAt()
        );
        tokenRepository.save(token);
    }

    @QueryHandler
    public Token on(GetTokenByTokenQuery query){
        return tokenRepository.findByToken(query.getToken());
    }


}
