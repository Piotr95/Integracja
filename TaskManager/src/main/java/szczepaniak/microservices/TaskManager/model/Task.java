package szczepaniak.microservices.TaskManager.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import static javax.persistence.EnumType.STRING;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name" )
    @NonNull
    @NotBlank(message = "Cen not be empty")
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(STRING)
    private TaskPriority taskPriority;

    @Column(name = "fk_assigment")
    private Long fk_assigment;

    public Task(@NonNull @NotBlank(message = "Cen not be empty") String name, String description, TaskPriority taskPriority) {
        this.name = name;
        this.description = description;
        this.taskPriority = taskPriority;
    }
}
