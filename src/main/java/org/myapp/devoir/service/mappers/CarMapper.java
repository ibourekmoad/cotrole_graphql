package org.myapp.devoir.service.mappers;


import org.modelmapper.ModelMapper;
import org.myapp.devoir.dao.entities.Car;
import org.myapp.devoir.service.dtos.CarDTO;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    private ModelMapper modelMapper = new ModelMapper();

    public CarDTO fromCarToCarDTO(Car car){
        return this.modelMapper.map(car, CarDTO.class);
    }

    public Car fromCarDTOtoCar(CarDTO carDTO){
        return this.modelMapper.map(carDTO, Car.class);
    }
}
