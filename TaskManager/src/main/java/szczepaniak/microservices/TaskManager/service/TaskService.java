package szczepaniak.microservices.TaskManager.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczepaniak.microservices.TaskManager.repository.AssigmentRepository;
import szczepaniak.microservices.TaskManager.repository.TaskRepository;
import szczepaniak.microservices.TaskManager.infrastructure.exeption.ResourceNotFoundException;
import szczepaniak.microservices.TaskManager.model.Assigment;
import szczepaniak.microservices.TaskManager.model.Task;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    TaskRepository taskRepository;
    AssigmentRepository assigmentRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository, AssigmentRepository assigmentRepository) {
        this.taskRepository = taskRepository;
        this.assigmentRepository = assigmentRepository;
    }

    public Task create(Task task) {
        Long id= task.getFk_assigment();
       Assigment assigment=assigmentRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        assigment.addTask(task);
        task.setFk_assigment(id);
        Task save=taskRepository.save(task);
        assigmentRepository.save(assigment);
        return save;

    }

    public Task update(Task task) {
        if (taskRepository.existsById(task.getId())) {
            return taskRepository.save(task);
        } else {
            throw new ResourceNotFoundException();
        }
    }


    public void delete(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Task getById(long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(EntityNotFoundException::new);
    }
    public List<Task> getAll() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        return  Lists.newArrayList(taskIterable);
    }
}
