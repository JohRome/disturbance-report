package com.post.dtos;

import com.utils.Input;
import lombok.extern.slf4j.Slf4j;
import pojos.*;

@Slf4j
public class ReportDTOHandler {
    private final Input input;
    public ReportDTOHandler(Input input) {
        this.input = input;
    }

    public Person createPerson() {
        var personInfo = getPersonInfo();
        var address = getAddress();
        return new Person(personInfo, address);
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
