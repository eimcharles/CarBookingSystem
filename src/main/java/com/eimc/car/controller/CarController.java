package com.eimc.car.controller;

import com.eimc.car.dto.CarDTO;
import com.eimc.car.model.Car;
import com.eimc.car.service.CarService;
import com.eimc.common.domain.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createCar(@RequestBody CarDTO request) {

        Car car = request.toEntity();
        Car createdCar = carService.createCar(car);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("car" , CarDTO.fromEntity(createdCar)))
                        .message("Car created successfully")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.POST.name())
                        .build()
        );

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
