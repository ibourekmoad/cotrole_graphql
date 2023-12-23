package org.myapp.devoir.service;

import org.myapp.devoir.dao.entities.Car;
import org.myapp.devoir.dao.repositories.CarRepository;
import org.myapp.devoir.service.dtos.CarDTO;
import org.myapp.devoir.service.mappers.CarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarManagerAction implements CarManager{
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Autowired
    public CarManagerAction(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public boolean isMatriculeUnique(String matricule) {
        Car existingCar = carRepository.findByMatricule(matricule);
        return existingCar == null;
    }

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        if (isMatriculeUnique(carDTO.getMatricule())) {
            Car carToSave = carMapper.fromCarDTOtoCar(carDTO);
            Car savedCar = carRepository.save(carToSave);
            return carMapper.fromCarToCarDTO(savedCar);
        } else {
            // Handle the case when the matricule already exists
            throw new IllegalArgumentException("Matricule already exists");

        }
    }

    @Override
    public CarDTO getCarById(long carId) {
        return carRepository.findById(carId)
                .map(carMapper::fromCarToCarDTO)
                .orElse(null);
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carRepository.findAll()
                .stream()
                .map(carMapper::fromCarToCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO saveCarIfMatriculeUnique(CarDTO carDTO) {
        if (isMatriculeUnique(carDTO.getMatricule())) {
            Car carToSave = carMapper.fromCarDTOtoCar(carDTO);
            Car savedCar = carRepository.save(carToSave);
            return carMapper.fromCarToCarDTO(savedCar);
        } else {
            // Return null or handle the case where the matricule already exists
            return null;
        }
    }

    @Override
    public List<CarDTO> getCarsByModel(String model) {
        List<Car> cars = carRepository.findByModel(model);
        return cars.stream()
                .map(carMapper::fromCarToCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getCarsByModelAndPrice(String model, double price) {
        List<Car> cars = carRepository.findByModelAndPrice(model, price);
        return cars.stream()
                .map(carMapper::fromCarToCarDTO)
                .collect(Collectors.toList());
    }
}
