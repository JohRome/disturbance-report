package com.post.app;

import com.post.api.ApacheKafkaAPI;
import com.post.dtos.ReportDTO;
import com.post.interfaces.Serialized;
import com.post.utils.Input;
import com.post.utils.Output;

import java.io.IOException;

/**
 * Main application class responsible for user interaction and filing complaints through the ApacheKafkaAPI class.
 */
public class Application {

    private final ApacheKafkaAPI apacheKafkaAPI;
    private final Input input;
    public Application(ApacheKafkaAPI apacheKafkaAPI, Input input) throws IOException {
        this.apacheKafkaAPI = apacheKafkaAPI;
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
            Output.printMenu();
            switch (input.integerInput()) {
                case 1 -> fileAComplaint();
                case 2 -> System.exit(0);
            }
        }
    }

    /**
     * Allows the user to file a complaint by providing victim information and event details.
     *
     * @throws IOException If an IO error occurs.
     */
    private void fileAComplaint() throws IOException {
        String victimFirstName = input.stringInput("Set victim's first name -> ");
        String victimLastName = input.stringInput("Set victim's last name -> ");
        String victimAddress = input.stringInput("Set victim's address -> ");
        String victimEventDetails = input.stringInput("Describe the event by providing details. What happened? ->");

        Serialized reportForm =  new ReportDTO(victimFirstName, victimLastName, victimAddress, victimEventDetails);

        String json = apacheKafkaAPI.serializeToJSON(reportForm);
        apacheKafkaAPI.postRequest(json, "publish");
    }
}

