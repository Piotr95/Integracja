package szczepaniak.microservices.TaskManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import szczepaniak.microservices.TaskManager.service.TaskService;
import szczepaniak.microservices.TaskManager.model.Task;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()

    public List<Task> getTasks() {
        return taskService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Task getById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Task create( @RequestBody Task task) {
        return taskService.create(task);
    }
    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Task update( @RequestBody Task task) {
        return taskService.update(task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        taskService.delete(id);
    }
}