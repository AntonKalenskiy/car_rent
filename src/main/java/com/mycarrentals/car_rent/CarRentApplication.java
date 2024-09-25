package com.mycarrentals.car_rent;

import com.mycarrentals.car_rent.model.Car;
import com.mycarrentals.car_rent.model.Payment;
import com.mycarrentals.car_rent.model.Rental;
import com.mycarrentals.car_rent.model.User;
import com.mycarrentals.car_rent.repository.CarRepository;
import com.mycarrentals.car_rent.repository.PaymentRepository;
import com.mycarrentals.car_rent.repository.RentalRepository;
import com.mycarrentals.car_rent.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@SpringBootApplication
public class CarRentApplication {

    public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CarRentApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CarRepository carRepository, UserRepository userRepository, RentalRepository rentalRepository, PaymentRepository paymentRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			carRepository.save(new Car("Camry", "Toyota", Car.CarType.SEDAN, 10, new BigDecimal("50.00"), false));
			carRepository.save(new Car("Civic", "Honda", Car.CarType.SEDAN, 5, new BigDecimal("45.00"), false));
			carRepository.save(new Car("CR-V", "Honda", Car.CarType.SUV, 8, new BigDecimal("60.00"), false));
			carRepository.save(new Car("Model 3", "Tesla", Car.CarType.SEDAN, 7, new BigDecimal("70.00"), false));
			carRepository.save(new Car("X5", "BMW", Car.CarType.SUV, 4, new BigDecimal("80.00"), false));

			userRepository.save(new User("batman@gmail.org", "Bruce", "Wayne", passwordEncoder.encode("12345678"), Set.of(User.Role.MANAGER, User.Role.CUSTOMER)));
			userRepository.save(new User("superman@gmail.org", "Clark", "Kent", passwordEncoder.encode("87654321"), Set.of(User.Role.CUSTOMER)));

			rentalRepository.save(new Rental(1L,null, null, LocalDate.of(2024, 6, 10), LocalDate.of(2024,6,1)));
			rentalRepository.save(new Rental(2L, null, null, LocalDate.of(2024, 3, 10), LocalDate.of(2024,3,1)));
			rentalRepository.save(new Rental(3L, null, null, LocalDate.of(2024, 1, 10), LocalDate.of(2024,1,1)));

			paymentRepository.save(new Payment(Payment.Type.PAYMENT, Payment.Status.PAID, 1L, "cs_test_a1b2c3d4e5f6g7h8i9j0klmnopqrstuvwxyz", "https://checkout.stripe.com/pay/cs_test_a1b2c3d4e5f6g7h8i9j0klmnopqrstuvwxyz", new BigDecimal("355.50" )));
			paymentRepository.save(new Payment(Payment.Type.PAYMENT, Payment.Status.PAID, 2L, "cs_test_a1b2c3d4e5f6g7h8i9j0klmnopqrstuvwxyz", "https://checkout.stripe.com/pay/cs_test_a1b2c3d4e5f6g7h8i9j0klmnopqrstuvwxyz", new BigDecimal("355.50" )));
			paymentRepository.save(new Payment(Payment.Type.PAYMENT, Payment.Status.PENDING, 3L, "cs_test_a1b2c3d4e5f6g7h8i9j0klmnopqrstuvwxyz", "https://checkout.stripe.com/pay/cs_test_a1b2c3d4e5f6g7h8i9j0klmnopqrstuvwxyz", new BigDecimal("355.50" )));
		};
	}
}
