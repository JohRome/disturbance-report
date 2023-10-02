package com.post.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * Service class responsible for consuming JSON messages from a Kafka topic and printing them to the console.
 */
@Service
@Slf4j
public class ConsoleConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleConsumer.class);

    /**
     * Kafka listener method that consumes JSON messages from the "disturbance-reports" topic and prints them to the console.
     *
     * @param jsonMessage The JSON message received from Kafka.
     */
    @KafkaListener(topics = "disturbance-reports", groupId = "console")
    public void printToConsole(String jsonMessage) {
        LOGGER.info("This was consumed -> {}", jsonMessage);
    }

    public static void printAllMessagesInTopic(String topicName) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093, localhost:9094");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "all-messages-in-topic");


        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topicName));

        consumer.poll(0); // Seek to the beginning of the topic
        consumer.seekToBeginning(consumer.assignment());

        while (true) {
            var records = consumer.poll(Duration.ofMillis(100)); // Poll for new records
            if (records.isEmpty()) {
                break; // No more records
            }
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Retrieved message: " + record.value());
            }
        }
        consumer.close();
    }
}

