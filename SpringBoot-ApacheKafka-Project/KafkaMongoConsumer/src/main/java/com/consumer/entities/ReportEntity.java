package com.consumer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Document(collection = "reports")
public class ReportEntity {

    @Id
    private String id;
    private String victimFirstName;
    private String victimLastName;
    private String address;
    private String eventDetails;
    private boolean isSolved;

}
