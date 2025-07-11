package com.learn.kafka.demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learn.kafka.demo.model.User;
import com.learn.kafka.demo.repository.UserRepository;

/**
 * Service class responsible for consuming User messages from Kafka topic
 * and storing them in H2 database
 */
@Service
public class UserConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);

    private final UserRepository userRepository;

    public UserConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Listens to the user-topic and processes incoming User messages
     *
     * @param user The user object received from Kafka
     */
    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(User user) {
        LOGGER.info("Consumed message from topic: {}", user);

        try {
            // Save the user to H2 database
            User savedUser = userRepository.save(user);
            LOGGER.info("Successfully saved user to database with ID: {}", savedUser.getId());
        } catch (Exception e) {
            LOGGER.error("Error while saving user to database: {}", e.getMessage());
        }
    }
}
