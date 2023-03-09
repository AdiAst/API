package com.lithan.xyz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lithan.xyz.entities.Car;
import com.lithan.xyz.service.WebService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private WebService carService;

    // retrieve all cars
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // retrieve a specific car by ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> car =  Optional.of(carService.getCarById(id));
        if (car.isPresent()) {
            return ResponseEntity.ok(car.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // create a new car
    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        Car newCar = carService.createCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCar);
    }

    // retrieve a specific car by search
    @GetMapping("/search")
	public List<Car> search(@RequestParam String keyword, 
							@RequestParam double min, 
							@RequestParam double max) {
		return carService.search(keyword, min, max);
	}
}
