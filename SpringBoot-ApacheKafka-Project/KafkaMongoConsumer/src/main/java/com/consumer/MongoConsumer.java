package com.consumer;

import com.consumer.entities.ReportEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MongoConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MongoConsumer.class);
    private final ObjectMapper objectMapper;
    private final ReportRepository reportRepository;

    @Autowired
    public MongoConsumer(ObjectMapper objectMapper, ReportRepository reportRepository) {
        this.objectMapper = objectMapper;
        this.reportRepository = reportRepository;
    }

    @KafkaListener(topics = "disturbance-reports", groupId = "mongo")
    public void mongoSave(String jsonMessage) {
        if (isJsonCorrect(jsonMessage)) {
            try {
                ReportEntity reportEntity = objectMapper.readValue(jsonMessage, ReportEntity.class);
                reportRepository.save(reportEntity);
                LOGGER.info("Entity sent to MongoDB");
            } catch (JsonProcessingException | MongoException e) {
                LOGGER.error("Consumer error -> ", e);
            }
        } else {
            LOGGER.info("Error with JSON parsing");
        }
    }
    public boolean isJsonCorrect(String jsonMessage) {
        return jsonMessage != null && !jsonMessage.isEmpty();
    }
}