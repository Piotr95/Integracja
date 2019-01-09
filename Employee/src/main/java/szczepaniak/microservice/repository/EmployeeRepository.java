package szczepaniak.microservice.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.microservice.model.Employee;


import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {
    Optional<Employee> findByFirstName(String firstName);

    boolean existsById(Long id);
}
