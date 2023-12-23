package org.myapp.devoir.dao.repositories;

import org.myapp.devoir.dao.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByMatricule(String matricule);
    List<Car> findByModel(String model);
    List<Car> findByModelAndPrice(String model, double price);
}
