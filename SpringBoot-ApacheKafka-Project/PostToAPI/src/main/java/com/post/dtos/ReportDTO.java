package com.post.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.post.interfaces.Serialized;

import pojos.Person;

/**
 * Data Transfer Object (DTO) class representing a report to be serialized and sent to Kafka.
 */
public class ReportDTO implements Serialized {

//    /**
//     * The unique identifier for the report (not serialized).
//     */
//    @JsonProperty
//    private String id; // mongoDB autogenerated object id
//    @JsonProperty
//    private String victimFirstName;
//    @JsonProperty
//    private String victimLastName;
//    @JsonProperty
//    private String address;
//    @JsonProperty
//    private String eventDetails;
//    @JsonProperty
//    private boolean isSolved; // default value is false and later set to true by MongoDB admin
//    public ReportDTO(String victimFirstName, String victimLastName, String address, String eventDetails) {
//        this.victimFirstName = victimFirstName;
//        this.victimLastName = victimLastName;
//        this.address = address;
//        this.eventDetails = eventDetails;
//    }

    /**
     * The unique identifier for the report (not serialized).
     */
    @JsonProperty
    private String id; // mongoDB autogenerated object id
    @JsonProperty
    private Person person;
    @JsonProperty
    private String address;
    @JsonProperty
    private String eventDetails;
    @JsonProperty
    private boolean isSolved; // default value is false and later set to true by MongoDB admin
    public ReportDTO(Person person, String address, String eventDetails) {
        this.person = person;
        this.address = address;
        this.eventDetails = eventDetails;
    }
}
