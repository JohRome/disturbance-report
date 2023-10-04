package pojos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TheReportedPerson{

    private PersonInfo personInfo;
    private Address address;
    public TheReportedPerson(PersonInfo personInfo, Address address) {
        this.personInfo = personInfo;
        this.address = address;
    }
}
