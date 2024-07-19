package com.mycarrentals.car_rent;

import com.mycarrentals.car_rent.model.Car;
import com.mycarrentals.car_rent.model.User;
import com.mycarrentals.car_rent.repository.CarRepository;
import com.mycarrentals.car_rent.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class CarRentApplication {

    public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CarRentApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CarRepository carRepository, UserRepository userRepository) {
		return args -> {
			carRepository.save(new Car("Camry", "Toyota", Car.CarType.SEDAN, 10, new BigDecimal("50.00"), false));
			carRepository.save(new Car("Civic", "Honda", Car.CarType.SEDAN, 5, new BigDecimal("45.00"), false));
			carRepository.save(new Car("CR-V", "Honda", Car.CarType.SUV, 8, new BigDecimal("60.00"), false));
			carRepository.save(new Car("Model 3", "Tesla", Car.CarType.SEDAN, 7, new BigDecimal("70.00"), false));
			carRepository.save(new Car("X5", "BMW", Car.CarType.SUV, 4, new BigDecimal("80.00"), false));

			userRepository.save(new User("batman@gmail.org", "Bruce", "Wayne", "12345678", User.Role.MANAGER));
			userRepository.save(new User("superman@gmail.org", "Clark", "Kent", "333", User.Role.CUSTOMER));
		};
	}
}
