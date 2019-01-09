package szczepaniak.microservices.officeManagement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
     private String location;
    @Column(unique = true)
    @JsonProperty("employeeId")
    private Long employeeId=null;

    public Workplace(String location) {
        this.location = location;
    }

    public Workplace() {
    }
}
