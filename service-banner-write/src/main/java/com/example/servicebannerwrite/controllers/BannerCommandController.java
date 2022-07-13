package com.example.servicebannerwrite.controllers;

import coreapi.commands.CreateBannerCommand;
import coreapi.commands.DeleteBannerCommand;
import coreapi.commands.UpdateBannerCommand;
import coreapi.dtos.CreateBannerRequestDTO;
import coreapi.dtos.UpdateBannerRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping(path = "/banner/write")
public class BannerCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createBanner(@RequestBody CreateBannerRequestDTO request) {
        if(Objects.isNull(request))
            return "Customer can not be empty";
        try{
            CreateBannerCommand createBannerCommand = new CreateBannerCommand(
                    UUID.randomUUID().toString(),
                    request.getTitle(),
                    request.getDescription(),
                    request.getLocation(),
                    request.getType(),
                    request.getLink(),
                    request.getImage(),
                    new Date(),
                    true
            );
            commandGateway.send(createBannerCommand);
            return createBannerCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the banner.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateBanner(@RequestBody UpdateBannerRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Banner can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateBannerCommand updateBannerCommand = new UpdateBannerCommand(
                        request.getId(),
                        request.getTitle(),
                        request.getDescription(),
                        request.getLocation(),
                        request.getType(),
                        request.getLink(),
                        request.getImage(),
                        request.getActive()
                );
                commandGateway.send(updateBannerCommand);
                return updateBannerCommand;
            }else
            return "Banner not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating Banner with id " + id;
        }
    }


    @DeleteMapping(path = "/delete/{idBanner}")
    public Object deleteBanner(@PathVariable String idBanner) {
        if(idBanner.isEmpty())
            return "ID Banner can not be empty";
        try {
            DeleteBannerCommand deleteBannerCommand = new DeleteBannerCommand(idBanner);
            commandGateway.send(deleteBannerCommand);
            return deleteBannerCommand;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete banner with id " + idBanner;
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity=new ResponseEntity<String>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}