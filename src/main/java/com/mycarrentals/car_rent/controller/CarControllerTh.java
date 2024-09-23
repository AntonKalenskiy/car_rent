package com.mycarrentals.car_rent.controller;

import com.mycarrentals.car_rent.dto.request.CarThRequestDto;
import com.mycarrentals.car_rent.dto.response.CarResponseDto;
import com.mycarrentals.car_rent.model.Car;
import com.mycarrentals.car_rent.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cars-th")
public class CarControllerTh {
    private final CarService carService;
    private final String SORT_BY_ID = "?sort=id";
    private final String SORT_BY_BRAND = "?sort=brand";

    @GetMapping("/list")
    public String listCars(Model theModel, Pageable pageable) {
        List<CarResponseDto> cars = carService.findAllCars(pageable);
        theModel.addAttribute("cars", cars);
        return "cars/list-cars";
    }

    @GetMapping("/showFormForAdd")
    public String showForm(Model theModel) {
        CarThRequestDto car = new CarThRequestDto();
        car.setId(null);
        theModel.addAttribute("car", car);
        theModel.addAttribute("carTypes", Car.CarType.values());
        return "cars/car-form";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute ("car") @Valid CarThRequestDto carThRequestDto,
                          BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "cars/car-form";
        }
        carService.saveCarTh(carThRequestDto);
        return "redirect:/cars-th/list" + SORT_BY_ID;
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("carId") Long carId,
                                    Model theModel) {
        CarResponseDto car = carService.findById(carId);
        theModel.addAttribute("car", car);
        theModel.addAttribute("carTypes", Car.CarType.values());
        return "cars/car-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("carId") Long carId) {
        carService.deleteById(carId);
        return "redirect:/cars-th/list" + SORT_BY_ID;
    }
}
