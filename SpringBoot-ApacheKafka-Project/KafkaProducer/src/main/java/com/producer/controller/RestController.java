package com.producer.controller;

import com.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller class responsible for handling HTTP POST requests to publish JSON messages to a Kafka topic.
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/v1/reports")
@Slf4j
public class RestController {
    private final KafkaProducer kafkaProducer;
    public RestController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * Handles HTTP POST requests to publish JSON messages to the Kafka topic.
     *
     * @param jsonMessage The JSON message received in the request body.
     * @return A ResponseEntity indicating the status of the operation.
     */
    @PostMapping("/post")
    public ResponseEntity<String> send(@RequestBody String jsonMessage) {
        try {
            kafkaProducer.sendToTopic(jsonMessage);
            return ResponseEntity.ok("JSON message sent to topic");
        } catch (ResponseStatusException e) {
            log.error("Controller error -> ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
}

