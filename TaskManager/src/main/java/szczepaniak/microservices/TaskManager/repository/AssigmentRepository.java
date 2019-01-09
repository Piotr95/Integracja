package szczepaniak.microservices.TaskManager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.microservices.TaskManager.model.Assigment;

import java.util.List;

@Repository
public interface AssigmentRepository extends CrudRepository<Assigment,Long> {
     List<Assigment> getAllByEmployeeIdEquals(long employeeId) ;
}
