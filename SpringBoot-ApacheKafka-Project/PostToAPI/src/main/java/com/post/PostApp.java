package com.post;

import com.post.api.ApacheKafkaAPI;
import com.post.app.Application;

import com.post.consumer.ConsoleConsumer;
import com.post.dtos.ReportDTOHandler;
import com.post.interfaces.Sender;
import com.utils.Input;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.post.consumer")
public class PostApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PostApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        var input = new Input();
        new Application(new ApacheKafkaAPI(), input, new ReportDTOHandler(input), new ConsoleConsumer());
    }
}