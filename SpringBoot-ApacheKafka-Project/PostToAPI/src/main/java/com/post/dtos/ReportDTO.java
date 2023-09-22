package com.post.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.post.interfaces.Serialized;

public class ReportDTO implements Serialized {

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
