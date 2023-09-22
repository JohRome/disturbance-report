package com.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api/v1/reports")
public class RestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestController.class);
    private final KafkaProducer kafkaProducer;

    public RestController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> send(@RequestBody String jsonMessage) {
        try {
            kafkaProducer.sendToTopic(jsonMessage);
            return ResponseEntity.ok("JSON message sent to topic");
        } catch(ResponseStatusException e) {
            LOGGER.error("Controller error -> ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
}
