package szczepaniak.microservices.EmployeeAvailability.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import szczepaniak.microservices.EmployeeAvailability.service.AbsenceService;
import szczepaniak.microservices.EmployeeAvailability.model.Absence;

import java.util.List;

@RestController
@RequestMapping("/absences")
public class AbsenceController {
    private final AbsenceService absenceService;

    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping()
    public List<Absence> getAbsences() {
        return absenceService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Absence getById(@PathVariable("id") Long id) {
        return absenceService.getById(id);
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public Absence update(@RequestBody Absence absence) {
        return absenceService.update(absence);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        absenceService.delete(id);
    }

    @GetMapping("/employee/{id}")
    public String getAvailability(@PathVariable Long id) {
        return (absenceService.isEmployeeAvailableNow(id)) ? "Available now" : "Not available now ";
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping()
    public String create(@RequestBody Absence absence) {
        if (absenceService.save(absence) != null) {
            return "Your leave has been reserved";
        }
        return "Reservation error";
    }
}
