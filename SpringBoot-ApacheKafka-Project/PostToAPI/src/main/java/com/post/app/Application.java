package com.post.app;

import com.post.api.ApacheKafkaAPI;
import com.post.dtos.ReportDTO;
import com.post.interfaces.Serialized;
import com.post.utils.Input;
import com.post.utils.Output;

import java.io.IOException;

public class Application {

    private final ApacheKafkaAPI apacheKafkaAPI;
    private final Input input;

    public Application(ApacheKafkaAPI apacheKafkaAPI, Input input) throws IOException {
        this.apacheKafkaAPI = apacheKafkaAPI;
        this.input = input;
        startApp();
    }


    private void startApp() throws IOException {
        boolean isDone = false;

        while (!isDone) {
            Output.printMenu();
            switch (input.integerInput("Enter a choice")) {
                case 1 -> fileAComplaint();
                case 2 -> isDone = true;
            }
        }
    }
    private void fileAComplaint() throws IOException {
        String victimFirstName = input.stringInput("Set victim's first name");
        String victimLastName = input.stringInput("Set victim's last name");
        String victimAddress = input.stringInput("Set victim's address");
        String victimEventDetails = input.stringInput("Describe the event by providing details. What happened?");

        Serialized reportForm =  new ReportDTO(victimFirstName, victimLastName, victimAddress, victimEventDetails);

        String json = apacheKafkaAPI.serializeToJSON(reportForm);
        apacheKafkaAPI.postRequest(json, "publish");
    }
}
