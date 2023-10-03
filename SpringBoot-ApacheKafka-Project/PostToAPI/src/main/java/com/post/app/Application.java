package com.post.app;

import com.post.consumer.ConsoleConsumer;
import com.post.dtos.ReportDTO;
import com.post.interfaces.Sender;
import com.post.interfaces.Serialized;

import com.utils.Output;
import com.utils.Input;

import java.io.IOException;

/**
 * Main application class responsible for user interaction and filing complaints through the ApacheKafkaAPI class.
 */
public class Application {
    private final Sender sender;
    private final Input input;
    public Application(Sender sender, Input input) throws IOException {
        this.sender = sender;
        this.input = input;
        startApp();
    }

    /**
     * Starts the application and handles user interaction.
     *
     * @throws IOException If an IO error occurs.
     */
    private void startApp() throws IOException {
        while (true) {
            Output.printPostToAPIMenu();
            switch (input.integerInput()) {
                case 1 -> fileADisturbanceReport();
                case 2 -> ConsoleConsumer.printAllMessagesInTopic("disturbance-reports", "all-messages");
                case 3 -> System.exit(0);
            }
        }
    }

    /**
     * Allows the user to file a complaint by providing victim information and event details.
     *
     * @throws IOException If an IO error occurs.
     */
    private void fileADisturbanceReport() throws IOException {
        String victimFirstName = input.stringInput("Set victim's first name -> ");
        String victimLastName = input.stringInput("Set victim's last name -> ");
        String victimAddress = input.stringInput("Set victim's address -> ");
        String victimEventDetails = input.stringInput("Describe the event by providing details. What happened? -> ");

        // id and isSolved are not supposed to be set, because they're handled by MongoConsumer.class
        Serialized reportForm =  new ReportDTO(victimFirstName, victimLastName, victimAddress, victimEventDetails);

        String json = sender.serializeToJSON(reportForm);
        sender.postRequest(json,"publish");
    }
}

