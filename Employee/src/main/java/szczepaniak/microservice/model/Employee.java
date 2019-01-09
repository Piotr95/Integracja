package szczepaniak.microservice.model;


import lombok.Data;

import javax.persistence.*;
import static javax.persistence.EnumType.STRING;

@Entity(name = "employee")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Enumerated(STRING)
    private JobPosition jobPosition;

}

