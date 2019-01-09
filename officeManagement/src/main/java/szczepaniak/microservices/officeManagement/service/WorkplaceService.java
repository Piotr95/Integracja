package szczepaniak.microservices.officeManagement.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczepaniak.microservices.officeManagement.repository.WorkplaceRepository;
import szczepaniak.microservices.officeManagement.infrastructure.exeption.ResourceNotFoundException;
import szczepaniak.microservices.officeManagement.model.Workplace;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Service
public class WorkplaceService {

    WorkplaceRepository workplaceRepository;

    @Autowired
    public WorkplaceService(WorkplaceRepository workplaceRepository) {
        this.workplaceRepository = workplaceRepository;
    }

    public Workplace getById(Long id) {
        return workplaceRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    public List<Workplace> getAll() {
        Iterable<Workplace> workplaces = workplaceRepository.findAll();
        return Lists.newArrayList(workplaces);
    }

    public Workplace update(Workplace assigment) {
        if (workplaceRepository.existsById(assigment.getId())) {
            return workplaceRepository.save(assigment);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Workplace create(Workplace workplace) {


        return workplaceRepository.save(workplace);


    }

    public void delete(Long id) {
        if (workplaceRepository.existsById(id)) {
            workplaceRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Workplace getWorkplaceOfEmployee(long employeeId) {
        return workplaceRepository.findWorkplaceByEmployeeIdEquals(employeeId)
                .orElseThrow(ResourceNotFoundException::new);
    }


    public List<Workplace> getUnassignedWorkplaces() {
        return workplaceRepository.findAllByEmployeeIdIsNullOrEmployeeIdEquals(0l);
    }

    public void assignWorkplace(Long employeeId, long workplaceId) {
        if (employeeWithoutWorkplace(employeeId) && workplaceIsUnassigned(workplaceId)) {
            Workplace workplaceToAssign = getWorkplaceById(workplaceId);
            workplaceToAssign.setEmployeeId(employeeId);
            workplaceRepository.save(workplaceToAssign);
        }
    }

    public void reassignWorkplace(Long employeeId, long workplaceId) {
        if (employeeWithoutWorkplace(employeeId) && workplaceExists(workplaceId)) {
            Workplace workplaceToAssign = getWorkplaceById(workplaceId);
            workplaceToAssign.setEmployeeId(employeeId);
            workplaceRepository.save(workplaceToAssign);
        }
    }

    public Workplace getWorkplaceById(long workplaceId) {
        Optional<Workplace> workplace = workplaceRepository.findById(workplaceId);
        return workplace.orElseThrow(ResourceNotFoundException::new);
    }

    private boolean workplaceIsUnassigned(long workplaceId) {
        if (!workplaceExists(workplaceId)) {
            return false;
        }
        return getUnassignedWorkplaces().contains(getWorkplaceById(workplaceId));
    }

    private boolean workplaceExists(long workplaceId) {
        return getWorkplaceById(workplaceId) != null;
    }

    private boolean employeeWithoutWorkplace(Long employeeId) {
        return getWorkplaceOfEmployee(employeeId) == null;
    }

    @PostConstruct
    public String init() throws Exception {
        IntStream.range(1, 8)
                .forEach(i -> this.create(new Workplace("First floor " + i + " on  the left  ")));
        return "Dane";

    }
}
