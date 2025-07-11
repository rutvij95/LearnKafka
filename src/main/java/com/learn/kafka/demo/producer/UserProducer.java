package com.learn.kafka.demo.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.learn.kafka.demo.model.User;

/**
 * Service class responsible for producing User messages to Kafka topic
 */
@Service
public class UserProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProducer.class);

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, User> kafkaTemplate;

    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a user object to the Kafka topic
     *
     * @param user The user object to send
     */
    public void sendMessage(User user) {
        LOGGER.info("Producing message to topic {}: {}", topicName, user);

        // Create message with headers
        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        // Send message to Kafka topic
        kafkaTemplate.send(message);

        LOGGER.info("Message sent successfully to topic: {}", topicName);
    }
}
