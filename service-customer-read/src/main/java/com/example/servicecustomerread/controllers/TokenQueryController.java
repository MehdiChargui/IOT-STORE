package com.example.servicecustomerread.controllers;

import com.example.servicecustomerread.entites.Token;
import coreapi.querys.GetTokenByTokenQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping(path = "/token/read")
public class TokenQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping("/getTokenByToken/{token}")
    public Object getTokenByToken(@PathVariable String token) {
        try {
            Token response =queryGateway.query(new GetTokenByTokenQuery(token),ResponseTypes.instanceOf(Token.class)).join();
            if(Objects.isNull(response))
                return "Token not found with id " + token;
            else
                return response;
        }catch (Exception ex){
            return ex.getMessage() +"Some error occurred while retrieving the token.";
        }
    }

}
