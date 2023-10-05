package com.post.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import static com.utils.JSONFormatter.prettyJSON;

/**
 * Service class responsible for consuming JSON messages from a Kafka topic and printing them to the console.
 */
@Service
@Slf4j
public class ConsoleConsumer {

    /**
     * Prints all messages in a Kafka topic to the console.
     *
     * @param topicName The name of the topic to which the consumer will subscribe.
     * @param groupId   The consumer group ID.
     */
    public void printAllMessagesInTopic(String topicName, String groupId) { // Inspiration from: Teacher Marcus.H and ChatGPT
        Consumer<String, String> consumer = consumerSetUp(topicName, groupId);

        log.info("Messages in the topic:");
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            if (records.isEmpty()) {
                break;
            }
            for (ConsumerRecord<String, String> record : records) {
                // static method from JSONFormatter.class in the Utilities module
                prettyJSON(record.value());
            }
        }
        consumer.close();
    }

    /**
     * Creates a Kafka consumer and subscribes to a specified topic, read from the beginning.
     *
     * @param topicName The name of the topic to which the consumer will subscribe.
     * @param groupId   The consumer group ID.
     */

    private Consumer<String, String> consumerSetUp(String topicName, String groupId) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092, localhost:9093, localhost:9094");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);


        Consumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topicName));

        consumer.poll(0);
        consumer.seekToBeginning(consumer.assignment());
        return consumer;
    }
}

