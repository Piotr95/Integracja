package szczepaniak.microservices.TaskManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import szczepaniak.microservices.TaskManager.service.AssigmentService;
import szczepaniak.microservices.TaskManager.model.Assigment;


import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
    @RequestMapping("/assigments")
    public class AssigmentController {
        private AssigmentService assigmentService;

        @Autowired
        public AssigmentController(AssigmentService assigmentService) {
            this.assigmentService = assigmentService;
        }

        @GetMapping()
        public List<Assigment> getAssigments() {
            return assigmentService.getAll();
        }

        @GetMapping("/{id}")
        @ResponseStatus(value = HttpStatus.OK)
        public Assigment getById(@PathVariable("id") Long id) {
            return assigmentService.getById(id);
        }

        @PostMapping()
        @ResponseStatus(value = HttpStatus.CREATED)
        public Assigment create( @RequestBody Assigment assigment) {
            return assigmentService.create(assigment);
        }
        @PutMapping()
        @ResponseStatus(value = HttpStatus.OK)
        public Assigment update( @RequestBody Assigment assigment) {
            return assigmentService.update(assigment);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(value = HttpStatus.NO_CONTENT)
        public void delete(@PathVariable("id") Long id) {
            assigmentService.delete(id);
        }


    @PostMapping( "/sign/{id}")
    public String sign(@PathVariable("id") Long id){
            assigmentService.signAssigment(id);
            return "assigment has been signed if  is not empty or  already signed";
        }


    }


