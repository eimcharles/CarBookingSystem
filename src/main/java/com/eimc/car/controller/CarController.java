package com.eimc.car.controller;

import com.eimc.car.dto.CarDTO;
import com.eimc.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody CarDTO request) {
        ///  TODO: Implement Logic
        return null;
    }

    @GetMapping

    /**
     *      Use requestParams
     *      to get the cars by
     *      fuel type (Gas, Electric)
     * */

    public ResponseEntity<?> getCars(){
        ///  TODO: Implement Logic
        return null;
    }

    @GetMapping("/{carId}")
    public ResponseEntity<?> getCarById(@PathVariable UUID carId){
        ///  TODO: Implement Logic
        return null;
    }

    @PatchMapping("/{carId}")
    public ResponseEntity<?> updateCarById(
            @PathVariable UUID carId,
            @RequestBody CarDTO request){
        ///  TODO: Implement Logic
        return null;
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<?> deleteCarById(@PathVariable UUID carId){
        ///  TODO: Implement Logic
        return null;
    }

}
