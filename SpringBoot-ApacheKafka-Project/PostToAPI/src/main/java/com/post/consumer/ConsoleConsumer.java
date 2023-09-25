package com.post.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsoleConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleConsumer.class);

    @KafkaListener(topics = "disturbance-reports", groupId = "console")
    public void printToConsole(String jsonMessage) {
        LOGGER.info("This was consumed -> {} ", jsonMessage);
    }
}
