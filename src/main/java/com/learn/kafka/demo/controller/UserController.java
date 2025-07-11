package com.learn.kafka.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.kafka.demo.model.User;
import com.learn.kafka.demo.producer.UserProducer;
import com.learn.kafka.demo.repository.UserRepository;

/**
 * REST controller for User management operations
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserProducer userProducer;
    private final UserRepository userRepository;

    public UserController(UserProducer userProducer, UserRepository userRepository) {
        this.userProducer = userProducer;
        this.userRepository = userRepository;
    }

    /**
     * API endpoint to manually send a user to Kafka
     *
     * @param user The user object to be sent to Kafka
     * @return Response message
     */
    @PostMapping("/publish")
    public ResponseEntity<String> publishUser(@RequestBody User user) {
        userProducer.sendMessage(user);
        return ResponseEntity.ok("User message sent to Kafka topic");
    }

    /**
     * API endpoint to retrieve all users from database
     *
     * @return List of all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }
}
