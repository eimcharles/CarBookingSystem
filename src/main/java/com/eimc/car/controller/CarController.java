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

        /// TODO add a location header for newly created resource

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
    public ResponseEntity<HttpResponse> getCars(){

        ///  TODO: Implement Logic for filtering using request
        ///  TODO: Look into pagination as fleet grows

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("cars" , carService.getCars()
                                .stream()
                                .map(CarDTO::fromEntity)
                                .toList()))
                        .message("Cars retrieved successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.GET.name())
                        .build()

        );

    }

    @GetMapping("/{carId}")
    public ResponseEntity<HttpResponse> getCarById(@PathVariable UUID carId){

        Car car = carService.getCarByCarId(carId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("car", CarDTO.fromEntity(car)))
                        .message("Car successfully retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString())
                        .requestMethod(RequestMethod.GET.name())
                        .build()
        );

    }

    @PatchMapping("/{carId}")
    public ResponseEntity<?> updateCarById(
            @PathVariable UUID carId,
            @RequestBody CarDTO request){
        ///  TODO: Implement Logic
        return null;
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<HttpResponse> deleteCarById(@PathVariable UUID carId){

        carService.deleteCarById(carId);

        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Car deleted successfully")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .path(ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                        .requestMethod(RequestMethod.DELETE.name())
                        .build()
        );

    }

}
