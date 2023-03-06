package com.lithan.xyz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lithan.xyz.entities.Car;
import com.lithan.xyz.entities.User;
import com.lithan.xyz.repository.CarRepository;
import com.lithan.xyz.repository.UserRepository;

@Service
public class WebService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Car getCarById(Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.orElse(null);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    
    public Car createCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> search(String keyword,double min, double max){
    	return carRepository.search(keyword, min, max);
    }


    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public String loginUser(String username, String password) {
    	System.out.println(username);
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return "Login Failed";
        }
        user.setLoggedIn(1);
        userRepository.save(user);
        return "Successfullly Login";
    }

    public void logoutUser(User user) {
        user.setLoggedIn(0);
        userRepository.save(user);
    }

}
