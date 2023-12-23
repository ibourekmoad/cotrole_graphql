package org.myapp.devoir.service;

import org.myapp.devoir.service.dtos.CarDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CarManager {

    CarDTO saveCar(CarDTO carDTO);

    CarDTO getCarById(long carId);

    List<CarDTO> getAllCars();

    CarDTO saveCarIfMatriculeUnique(CarDTO carDTO);

    List<CarDTO> getCarsByModel(String model);

    List<CarDTO> getCarsByModelAndPrice(String model, double price);
}
