package com.post.dtos;

import com.utils.Input;
import lombok.extern.slf4j.Slf4j;
import pojos.Address;
import pojos.TheReportedPerson;
import pojos.TheReportingPerson;
@Slf4j
public class ReportDTOHandler {
    private final Input input;

    public ReportDTOHandler(Input input) {
        this.input = input;
    }

    // TODO: Extract code duplication to a separate method
    public TheReportingPerson createReportingPerson() {

        String firstName = input.stringInput("Set your first name -> ");
        String lastName = input.stringInput("Set your last name -> ");

        String street = input.stringInput("Name of the street where you live -> ");
        String apartmentNumber = input.stringInput("Set your apartment number -> ");
        String city = input.stringInput("Set your city -> ");
        String zipCode = input.stringInput("Set your zip code -> ");

        var address = new Address(street, apartmentNumber, city, zipCode);

        return new TheReportingPerson(firstName, lastName, address);
    }

    public TheReportedPerson createReportedPerson() {

        String firstName = input.stringInput("Set the disturbing neighbour's first name -> ");
        String lastName = input.stringInput("Set the disturbing neighbour's's last name -> ");

        String street = input.stringInput("Name of the street where the the disturbing neighbour lives -> ");
        String apartmentNumber = input.stringInput("Set disturbing neighbour's apartment number -> ");
        String city = input.stringInput("Set disturbing neighbour's city -> ");
        String zipCode = input.stringInput("Set disturbing neighbour's zip code -> ");

        var address = new Address(street, apartmentNumber, city, zipCode);

        return new TheReportedPerson(firstName, lastName, address);
    }

    public String createEventDetails() {
        return input.stringInput("Set event details -> ");
    }
}
