package szczepaniak.microservices.officeManagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.microservices.officeManagement.model.Workplace;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkplaceRepository extends CrudRepository<Workplace,Long> {
    Optional<Workplace> findWorkplaceByEmployeeIdEquals(long employeeId);

    List<Workplace> findAllByEmployeeIdIsNullOrEmployeeIdEquals(Long id);
    Boolean existsByEmployeeId(Long id);
}
