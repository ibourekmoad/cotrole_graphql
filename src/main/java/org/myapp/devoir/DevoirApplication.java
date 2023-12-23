package org.myapp.devoir;

import org.myapp.devoir.dao.entities.Car;
import org.myapp.devoir.dao.repositories.CarRepository;
import org.myapp.devoir.service.CarManager;
import org.myapp.devoir.service.dtos.CarDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DevoirApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevoirApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(CarManager carManager) {
		return new DataLoader(carManager);
	}

	static class DataLoader implements CommandLineRunner {

		private final CarManager carManager;

		public DataLoader(CarManager carManager) {
			this.carManager = carManager;
		}

		@Override
		public void run(String... args) {
			carManager.saveCar(toCarDTO("Model 1", "Red", "ABC123", 20000.0));
			carManager.saveCar(toCarDTO("Model 2", "Blue", "XYZ456", 25000.0));
			carManager.saveCar(toCarDTO("Model 3", "Green", "DEF789", 18000.0));
			carManager.saveCar(toCarDTO("Model 4", "Black", "GHI101", 30000.0));
		}

		private CarDTO toCarDTO(String model, String color, String matricule, double price) {
			return CarDTO.builder()
					.model(model)
					.color(color)
					.matricule(matricule)
					.price(price)
					.build();
		}
	}

}


