package szczepaniak.microservices.EmployeeAvailability.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczepaniak.microservices.EmployeeAvailability.model.Absence;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface AbsenceRepository extends CrudRepository<Absence, Long> {
    List<Absence> findAbsenceByBeginningGreaterThanEqualAndEndLessThanEqualAndEmployeeIdEquals(LocalDate date, LocalDate theSameDate, long id);
}
