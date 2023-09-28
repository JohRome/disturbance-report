package com.post.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for consuming JSON messages from a Kafka topic and printing them to the console.
 */
@Service
public class ConsoleConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleConsumer.class);

    /**
     * Kafka listener method that consumes JSON messages from the "disturbance-reports" topic and prints them to the console.
     *
     * @param jsonMessage The JSON message received from Kafka.
     */
    @KafkaListener(topics = "disturbance-reports", groupId = "console")
    public void printToConsole(String jsonMessage) {
        LOGGER.info("This was consumed -> {} ", jsonMessage);
    }
}
