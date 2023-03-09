package com.lithan.xyz.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lithan.xyz.entities.Car;
import com.lithan.xyz.entities.Message;
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

    private static final String WEBHOOK_URL = "https://hooks.slack.com/services/%s";
    private static final Map<String,String> WEBHOOK_CHANNEL = Collections.singletonMap("ADI", "T04SVSND97T/B04T2QE8ZRR/RaBDr6woRMECjkB3S2GwixPc");
    
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
    
    public void sendMessage(String sender, Message messages) {
    	String msgSender = WEBHOOK_CHANNEL.get(sender);
    	String slackUrl = String.format(WEBHOOK_URL, msgSender);
    	
    	HttpHeaders httpHeader = new HttpHeaders();
    	httpHeader.setContentType(MediaType.APPLICATION_JSON);
    	
    	ObjectMapper objectMapper = new ObjectMapper();
    	
    	try {
			String body = objectMapper.writeValueAsString(messages);
			HttpEntity<String> httpEntity = new HttpEntity<String>(body,httpHeader);
			
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.exchange(slackUrl,HttpMethod.POST,httpEntity,String.class); 
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    }

	public static String getWebhookUrl() {
		return WEBHOOK_URL;
	}

	public static Map<String, String> getWebhookChannel() {
		return WEBHOOK_CHANNEL;
	}

}
