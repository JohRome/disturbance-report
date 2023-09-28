package com.post.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.post.interfaces.Serialized;
/**
 * Data Transfer Object (DTO) class representing a report to be serialized and sent to Kafka.
 */
public class ReportDTO implements Serialized {

    /**
     * The unique identifier for the report (not serialized).
     */
    @JsonProperty
    private String id;
    @JsonProperty
    private String victimFirstName;
    @JsonProperty
    private String victimLastName;
    @JsonProperty
    private String address;
    @JsonProperty
    private String eventDetails;
    @JsonProperty
    private boolean isSolved;
    public ReportDTO(String victimFirstName, String victimLastName, String address, String eventDetails) {
        this.victimFirstName = victimFirstName;
        this.victimLastName = victimLastName;
        this.address = address;
        this.eventDetails = eventDetails;
    }
}
