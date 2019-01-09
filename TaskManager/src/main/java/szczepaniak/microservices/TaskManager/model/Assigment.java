package szczepaniak.microservices.TaskManager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Assigment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assigment_id", unique = true, nullable = false)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)

    @JoinColumn(name = "fk_assigment", referencedColumnName = "assigment_id")
    Set<Task> tasks = new HashSet<>();
    private long employeeId;

    private Month month;
    private boolean isSigned;

    public void addTask(Task task) {
        tasks.add(task);
    }
}
