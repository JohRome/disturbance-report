package com.post.dtos;

import com.utils.Input;
import lombok.extern.slf4j.Slf4j;
import pojos.Address;
import pojos.PersonInfo;
import pojos.TheReportedPerson;
import pojos.TheReportingPerson;
@Slf4j
public class ReportDTOHandler {
    private final Input input;
    public ReportDTOHandler(Input input) {
        this.input = input;
    }

    public TheReportingPerson createReportingPerson() {
        log.info("Enter your details");
        var personInfo = getPersonInfo();
        var address = getAddress();


        return new TheReportingPerson(personInfo, address);
    }

    public TheReportedPerson createReportedPerson() {
        log.info("Enter the details of the person you are reporting");
        var personInfo = getPersonInfo();
        var address = getAddress();

        return new TheReportedPerson(personInfo, address);
    }
    private Address getAddress() {
        String street = input.stringInput("Set street name -> ");
        String apartmentNumber = input.stringInput("Set apartment number -> ");
        String city = input.stringInput("Set city -> ");
        String zipCode = input.stringInput("Set zip code -> ");
        return new Address(street, apartmentNumber, city, zipCode);
    }
    private PersonInfo getPersonInfo() {
        String firstName = input.stringInput("Set first name -> ");
        String lastName = input.stringInput("Set last name -> ");
        return new PersonInfo(firstName, lastName);
    }

    public String createEventDetails() {
        log.info("Give us a detailed description of the event, what happened?");
        return input.stringInput("Set event details -> ");
    }
}
