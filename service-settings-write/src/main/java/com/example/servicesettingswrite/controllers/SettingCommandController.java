package com.example.servicesettingswrite.controllers;

import coreapi.commands.CreateSettingCommand;
import coreapi.commands.DeleteSettingCommand;
import coreapi.commands.UpdateSettingCommand;
import coreapi.dtos.CreateSettingRequestDTO;
import coreapi.dtos.DeleteSettingRequestDTO;
import coreapi.dtos.UpdateSettingRequestDTO;
import coreapi.models.Links;
import coreapi.models.MailList;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping(path = "/setting/write")
public class SettingCommandController {
    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createSetting(@RequestBody CreateSettingRequestDTO request) {
        if(Objects.isNull(request))
            return "Setting can not be empty";
        try{
            CreateSettingCommand createSettingCommand = new CreateSettingCommand(
                UUID.randomUUID().toString(),
                request.getDescription(),
                mapIDsToMailLists(request.getMailList()),
                mapIDsToLinks(request.getLinks())
            );
            commandGateway.send(createSettingCommand);
            return createSettingCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the setting.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateSetting(@RequestBody UpdateSettingRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Setting can not be empty";
        try {
            if(request.getId().equals(id)) {
                UpdateSettingCommand updateSettingCommand = new UpdateSettingCommand(
                request.getId(),
                request.getDescription(),
                mapIDsToMailLists(request.getMailList()),
                mapIDsToLinks(request.getLinks())
                );
                commandGateway.send(updateSettingCommand);
                return updateSettingCommand;
            }else
                return "Setting not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating setting with id " + id;
        }
    }

    @DeleteMapping(path = "/delete")
    public CompletableFuture<String> deleteSetting(@RequestBody DeleteSettingRequestDTO request) {
        DeleteSettingCommand deleteSettingCommand = new DeleteSettingCommand(
                request.getId()
        );
        CompletableFuture<String> commandResponse = commandGateway.send(deleteSettingCommand);
        return commandResponse;
    }

    private List<MailList> mapIDsToMailLists(List<MailList> mailList) {
        if (Objects.isNull(mailList))
            return null;
        else {

            return mailList.stream().map(sc -> new MailList(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getMail(),
                    sc.getName()
            )).collect(Collectors.toList());
        }
    }


    private List<Links> mapIDsToLinks(List<Links> links) {
        if (Objects.isNull(links))
            return null;
        else {
            return links.stream().map(sc -> new Links(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getLink(),
                    sc.getName(),
                    sc.getActive()
            )).collect(Collectors.toList());
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