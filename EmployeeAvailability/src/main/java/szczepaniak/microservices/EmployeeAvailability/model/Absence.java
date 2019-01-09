package szczepaniak.microservices.EmployeeAvailability.model;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long employeeId;

    @Enumerated
    Cause cause;
    private String description;
    private LocalDate beginning;
    private LocalDate end;


    public Absence(){}


}
