package szczepaniak.microservices.officeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import szczepaniak.microservices.officeManagement.service.WorkplaceService;
import szczepaniak.microservices.officeManagement.model.Workplace;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/workplaces")
public class WorkplaceController {
    WorkplaceService workplaceService;
    @Autowired
    public WorkplaceController(WorkplaceService workplaceService) {
        this.workplaceService = workplaceService;
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Workplace create( @RequestBody Workplace workplace) {
        return workplaceService.create(workplace);
    }
    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Workplace update( @RequestBody Workplace workplace) {
        return workplaceService.update(workplace);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        workplaceService.delete(id);
    }

    @GetMapping()
    public List<Workplace> getWorkplaces(){
        return workplaceService.getAll();
    }

    @GetMapping("/unassigned")
    public List<Workplace> getUnassignedWorkplaces(){
        return workplaceService.getUnassignedWorkplaces();
    }

    @GetMapping("/employee/{id}")
    public Workplace getWorkplaceOfEmployee(@PathVariable("id") Long id) {
        return   workplaceService.getWorkplaceOfEmployee(id);

    }

    @PostMapping("/assign")
    public void assign(@RequestBody Workplace workplace){
        workplaceService.assignWorkplace(workplace.getEmployeeId(), workplace.getId());
    }

    @PostMapping("/reassign")
    public void reassign(@RequestBody Workplace workplace){
        workplaceService.reassignWorkplace(workplace.getEmployeeId(), workplace.getId());
    }
}

