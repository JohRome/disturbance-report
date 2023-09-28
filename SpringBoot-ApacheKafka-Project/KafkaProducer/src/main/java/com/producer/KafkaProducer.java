package com.producer;

import org.apache.kafka.common.errors.NetworkException;
import org.apache.kafka.common.errors.RecordTooLargeException;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.errors.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for producing and sending JSON messages to a Kafka topic.
 */
@Service
public class KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Constructor for KafkaProducer.
     *
     * @param kafkaTemplate The KafkaTemplate for sending messages to Kafka.
     */
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Sends a JSON message to the "disturbance-reports" Kafka topic.
     *
     * @param jsonMessage The JSON message to be sent.
     */
    public void sendToTopic(String jsonMessage) {

        try {
            Message<String> message = MessageBuilder // setting the payload to be sent and the topic to send it to
                    .withPayload(jsonMessage)
                    .setHeader(KafkaHeaders.TOPIC, "disturbance-reports")
                    .build();

            kafkaTemplate.send(message);
            LOGGER.info("JSON message was successfully sent to the topic");
        } catch (
                KafkaProducerException | NetworkException |
                RecordTooLargeException | SerializationException |
                TimeoutException e
        ) {
            LOGGER.error("Producer error -> ", e);
        }
    }
}

