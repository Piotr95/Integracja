package szczepaniak.microservices.TaskManager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.microservices.TaskManager.model.Task;
@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
}
