package szczepaniak.microservices.dto;

import lombok.Data;

import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;
@Data
public class Task {


    private String name;

    private String description;

    @Enumerated(STRING)
    private TaskPriority taskPriority;

    private Long fk_assigment;

}
