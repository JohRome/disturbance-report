package pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TheReportingPerson {

    private PersonInfo personInfo;
    private Address address;
    private String eventDetails;

    public TheReportingPerson(PersonInfo personInfo, Address address) {
        this.personInfo = personInfo;
        this.address = address;
    }
}
