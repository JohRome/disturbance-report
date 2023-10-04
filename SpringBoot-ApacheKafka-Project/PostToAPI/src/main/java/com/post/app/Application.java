package com.post.app;

import com.post.consumer.ConsoleConsumer;
import com.post.dtos.ReportDTO;
import com.post.dtos.ReportDTOHandler;
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
    private final ReportDTOHandler reportDTOHandler;
    public Application(Sender sender, Input input, ReportDTOHandler reportDTOHandler) throws IOException {
        this.sender = sender;
        this.input = input;
        this.reportDTOHandler = reportDTOHandler;
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
     * Creates a disturbance report, which is composed by the ReportDTOHandler class and sends it to Kafka API endpoint.
     *
     * @throws IOException If an IO error occurs.
     */
    private void fileADisturbanceReport() throws IOException {
        var theReportingPerson = reportDTOHandler.createPerson();
        var theReportedPerson = reportDTOHandler.createPerson();
        String eventDetails = reportDTOHandler.createEventDetails();

        Serialized reportForm = new ReportDTO(theReportingPerson, theReportedPerson, eventDetails);

        String json = sender.serializeToJSON(reportForm);
        sender.postRequest(json, "publish");
    }
}

