package szczepaniak.microservices.TaskManager.service;


import com.google.common.collect.Lists;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczepaniak.microservices.TaskManager.repository.AssigmentRepository;
import szczepaniak.microservices.TaskManager.infrastructure.exeption.ResourceNotFoundException;
import szczepaniak.microservices.TaskManager.model.Assigment;
import szczepaniak.microservices.TaskManager.model.Task;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class AssigmentService {

    AssigmentRepository assigmentRepository;

    @Autowired
    public AssigmentService(AssigmentRepository assigmentRepository) {
        this.assigmentRepository = assigmentRepository;
    }

    public Assigment create(Assigment assigment) {

        Task task = assigment.getTasks().stream().findFirst().orElseThrow(ResourceNotFoundException::new);
        task.setFk_assigment(assigment.getId());
        return assigmentRepository.save(assigment);
    }

    public Assigment update(Assigment assigment) {
        if (assigmentRepository.existsById(assigment.getId())) {
            return assigmentRepository.save(assigment);
        } else {
            throw new ResourceNotFoundException();
        }
    }


    public void delete(Long id) {
        if (assigmentRepository.existsById(id)) {
            assigmentRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Assigment getById(long id) {
        Optional<Assigment> assigment = assigmentRepository.findById(id);
        return assigment.orElseThrow(EntityNotFoundException::new);
    }

    public List<Assigment> getAll() {
        Iterable<Assigment> usersIterable = assigmentRepository.findAll();
        return Lists.newArrayList(usersIterable);
    }

    public List<Assigment> getAllEmployeeAssigments(long employeeId) {
        return assigmentRepository.getAllByEmployeeIdEquals(employeeId);
    }

    public Assigment signAssigment(Long id) {
        Assigment assigment = assigmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if(!assigment.getTasks().isEmpty()|| !assigment.isSigned()) {
            assigment.setSigned(true);
        }
        return assigmentRepository.save(assigment);
    }


}


