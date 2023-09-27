package com.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    @Bean
    public NewTopic reportsTopic() {
        return TopicBuilder
                .name("disturbance-reports")
                .replicas(4)
                .partitions(4)
                .build();
    }
}
