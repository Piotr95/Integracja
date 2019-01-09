package szczepaniak.microservices.EmployeeAvailability.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczepaniak.microservices.EmployeeAvailability.repository.AbsenceRepository;
import szczepaniak.microservices.EmployeeAvailability.model.Absence;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;

    @Autowired
    public AbsenceService(AbsenceRepository absenceRepository){
        this.absenceRepository = absenceRepository;
    }

    public Absence create(Absence absence) {
        return absenceRepository.save(absence);
    }

    public Absence update(Absence absence) {
        if (absenceRepository.existsById(absence.getId())) {
            return absenceRepository.save(absence);
        } else {
            throw new szczepaniak.microservices.TaskManager.infrastructure.exeption.ResourceNotFoundException();
        }
    }


    public void delete(Long id) {
        if (absenceRepository.existsById(id)) {
            absenceRepository.deleteById(id);
        } else {
            throw new szczepaniak.microservices.TaskManager.infrastructure.exeption.ResourceNotFoundException();
        }
    }

    public Absence getById(long id) {
        Optional<Absence> absence = absenceRepository.findById(id);
        return absence.orElseThrow(EntityNotFoundException::new);
    }
    public List<Absence> getAll() {
        Iterable<Absence> usersIterable = absenceRepository.findAll();
        return  Lists.newArrayList(usersIterable);
    }


    public boolean isEmployeeAvailableNow(long employeeId){
        return isEmployeeAvailableBetween(LocalDate.now(), LocalDate.now(), employeeId);
    }

    public String save(Absence absence){
        if(isEmployeeAvailableBetween(absence.getBeginning(), absence.getEnd(), absence.getEmployeeId())) {
         absenceRepository.save(absence);
            return "absence saved ";
        }
        return "You can not take absence";
    }


    private boolean isEmployeeAvailableBetween(LocalDate beginning, LocalDate end, long employeeId){
        List<Absence> absences = absenceRepository.findAbsenceByBeginningGreaterThanEqualAndEndLessThanEqualAndEmployeeIdEquals(beginning, end, employeeId);
        return absences.isEmpty();
    }

}
