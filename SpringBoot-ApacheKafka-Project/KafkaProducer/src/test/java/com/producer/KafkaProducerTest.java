package com.producer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class KafkaProducerTest {

    @MockBean
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaProducer producer;

    @Test
    void testSendToTopic() {
        when(kafkaTemplate.send(Mockito.<Message<Object>>any())).thenReturn(new CompletableFuture<>());
        producer.sendToTopic("Json Message");
        verify(kafkaTemplate).send(Mockito.<Message<Object>>any());
    }

    @Test
    void testSendToTopic2() {
        when(kafkaTemplate.send(Mockito.<Message<Object>>any())).thenThrow(new RuntimeException("kafka_topic"));
        assertThrows(RuntimeException.class, () -> producer.sendToTopic("Json Message"));
        verify(kafkaTemplate).send(Mockito.<Message<Object>>any());
    }

}