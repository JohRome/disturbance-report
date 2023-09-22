package com.consumer;

import com.consumer.entities.ReportEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MongoConsumerTest {

    @Autowired
    private MongoConsumer mongoConsumer;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    MongoConsumer mongoConsumerTest;
    private ReportEntity reportEntity;

    @BeforeEach
    void setUp() {
        reportEntity = new ReportEntity();
        reportEntity.setId("MongoId1337");
        reportEntity.setVictimFirstName("Wolfgang Amadeus");
        reportEntity.setVictimLastName("Mozart");
        reportEntity.setAddress("Vienna");
        reportEntity.setEventDetails("Antonio Salieri was my friend");
        reportEntity.setSolved(false);
        System.out.println("The following object has been created: " + reportEntity);
    }

    @AfterEach
    void tearDown() {
        reportRepository.delete(reportEntity);
    }

    @Test
    void testSavingEntityToMongoDB() throws JsonProcessingException {
        // given
        String jsonString = objectMapper.writeValueAsString(reportEntity);

        // when
        mongoConsumerTest.mongoSave(jsonString);

        // then
        assertTrue(reportRepository.existsById(reportEntity.getId()));
    }

    @Test
    void emptyJSONShouldNotSendToMongoDB() {
        // given
        reportEntity.setVictimLastName("");
        String emptyJson = reportEntity.getVictimLastName();
        reportEntity.setVictimLastName(emptyJson);

        // when
        mongoConsumerTest.mongoSave(emptyJson);

        // then
        assertFalse(reportRepository.existsById(reportEntity.getId()));
    }
    @Test
    void testIsJsonCorrect() {
        // given
        String correctJSON = reportEntity.getVictimFirstName();
        String nullJSON = null;
        String emptyJSON = "";

        // when, then
        assertTrue(mongoConsumer.isJsonCorrect(correctJSON));
        assertFalse(mongoConsumer.isJsonCorrect(nullJSON));
        assertFalse(mongoConsumer.isJsonCorrect(emptyJSON));
    }
}