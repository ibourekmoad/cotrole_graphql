package org.myapp.devoir.web;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;



import org.myapp.devoir.service.CarManager;
import org.myapp.devoir.service.dtos.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CarGraphQLController {

    private final CarManager carManager;
    @Autowired
    public CarGraphQLController(CarManager carManager) {
        this.carManager = carManager;
    }

    @QueryMapping
    public @ResponseBody
    CarDTO getCarByModel(@Argument String model) {
        return carManager.getCarsByModel(model).get(0);
    }
    @QueryMapping
    public @ResponseBody
    CarDTO getCarByModelAndPrice(@Argument String model, @Argument double price) {
        return carManager.getCarsByModelAndPrice(model, price).get(0);
    }
    @MutationMapping
    public @ResponseBody CarDTO saveCar(@Argument("car") CarDTO carDto) {
        return carManager.saveCar(carDto);
    }

}
