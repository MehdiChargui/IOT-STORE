package com.example.servicecategorywrite.controllers;

import coreapi.commands.CreateCategoryCommand;
import coreapi.commands.DeleteCategoryCommand;
import coreapi.commands.UpdateCategoryCommand;
import coreapi.dtos.CreateCategoryRequestDTO;
import coreapi.dtos.UpdateCategoryRequestDTO;
import coreapi.models.SubCategory;
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
@RequestMapping(path = "/category/write")
public class CategoryCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping(path = "/create")
    public Object createCategory(@RequestBody CreateCategoryRequestDTO request) {
        if(Objects.isNull(request))
            return "Category can not be empty";
        try{
            CreateCategoryCommand createCategoryCommand = new CreateCategoryCommand(
                    UUID.randomUUID().toString(),
                    request.getCategoryName(),
                    request.getImage(),
                    true,
                    mapIDsToCategories(request.getSubCategories())
            );
            commandGateway.send(createCategoryCommand);
            return createCategoryCommand;
        }catch (Exception ex){
            return ex.getMessage() + " Some error occurred while creating the category.";
        }
    }

    @PutMapping(path = "/update/{id}")
    public Object updateCategory(@RequestBody UpdateCategoryRequestDTO request,@PathVariable String id) {
        if(Objects.isNull(request))
            return "Category can not be empty";
        try {
            if(request.getId().equals(id)) {

                UpdateCategoryCommand updateCategoryCommand = new UpdateCategoryCommand(
                request.getId(),
                request.getCategoryName(),
                request.getImage(),
                request.getActive(),
                mapIDsToCategories(request.getSubCategories())
        );
                commandGateway.send(updateCategoryCommand);
                return updateCategoryCommand;
            }else
                return "Category not found with id " + id;
        }catch (Exception ex){
            return ex.getMessage()+" Error updating Category with id " + id;
        }
    }
    @DeleteMapping(path = "/delete/{idCategory}")
    public Object deleteCategory(@PathVariable String idCategory) {
        if(idCategory.isEmpty())
            return "ID Category can not be empty";
        try {
            DeleteCategoryCommand deleteCategoryCommand = new DeleteCategoryCommand(idCategory);
            CompletableFuture<String> commandResponse = commandGateway.send(deleteCategoryCommand);
            return commandResponse;
        }catch (Exception ex){
            return ex.getMessage()+" Could not delete category with id " + idCategory;
        }
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        ResponseEntity<String> entity=new ResponseEntity<String>(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    private List<SubCategory> mapIDsToCategories(List<SubCategory> categories) {
        if (Objects.isNull(categories))
            return null;
        else {

            return categories.stream().map(sc -> new SubCategory(
                    Optional.ofNullable(sc.getId()).orElse(UUID.randomUUID().toString()),
                    sc.getName(),
                    sc.getActive()
            )).collect(Collectors.toList());
        }
    }
}
