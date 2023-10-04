package com.post;

import com.post.api.ApacheKafkaAPI;
import com.post.app.Application;

import com.post.dtos.ReportDTOHandler;
import com.utils.Input;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostApp implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(PostApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        var input = new Input();
        new Application(new ApacheKafkaAPI(), input, new ReportDTOHandler(input));
    }
}