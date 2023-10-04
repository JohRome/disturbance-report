package com.consumer.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pojos.Address;
import pojos.Person;

/**
 * Entity class representing a report in a MongoDB collection.
 */
//@Setter
//@Getter
//@ToString
//@NoArgsConstructor
//@Document(collection = "reports")
//public class ReportEntity {
//
//    /**
//     * The unique identifier for the report.
//     */
//    @Id
//    private String id;
//    private String victimFirstName;
//    private String victimLastName;
//    private String address;
//    private String eventDetails;
//    private boolean isSolved;
//}

@Setter
@Getter
@ToString
@NoArgsConstructor
@Document(collection = "reports")
public class ReportEntity {

    /**
     * The unique identifier for the report.
     */
    @Id
    private String id;
    private Person person;
    private Address address;
    private String eventDetails;
    private boolean isSolved;
}
