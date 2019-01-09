package szczepaniak.microservices.dto;


import lombok.Data;

import javax.persistence.Enumerated;
import java.time.LocalDate;

@Data
public class Absence {
    private long employeeId;

    @Enumerated
    Cause cause;
    private String description;
    private LocalDate beginning;
    private LocalDate end;

}
