package com.learn.kafka.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn.kafka.demo.model.User;
import com.learn.kafka.demo.producer.UserProducer;

/**
 * This class initializes sample data for our application.
 * It automatically generates and sends sample users to Kafka when the application starts.
 */
@Configuration
public class DataInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataInitializer.class);

    /**
     * Creates and returns a CommandLineRunner bean that will be executed on application startup.
     * This will generate sample user data and send it to Kafka.
     *
     * @param userProducer The Kafka producer service for User messages
     * @return CommandLineRunner instance
     */
    @Bean
    public CommandLineRunner initData(UserProducer userProducer) {
        return args -> {
            LOGGER.info("Initializing sample user data...");

            // Generate and send sample users to Kafka
            userProducer.sendMessage(new User("John Doe", "john.doe@example.com", 30, "Engineering"));
            userProducer.sendMessage(new User("Jane Smith", "jane.smith@example.com", 28, "Marketing"));
            userProducer.sendMessage(new User("Bob Johnson", "bob.johnson@example.com", 35, "Finance"));
            userProducer.sendMessage(new User("Alice Brown", "alice.brown@example.com", 27, "Human Resources"));
            userProducer.sendMessage(new User("Charlie Wilson", "charlie.wilson@example.com", 32, "Product Management"));

            LOGGER.info("Sample user data has been sent to Kafka topic");
        };
    }
}
