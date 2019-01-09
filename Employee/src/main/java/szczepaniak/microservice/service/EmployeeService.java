package szczepaniak.microservice.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import szczepaniak.microservice.infrastructure.exeption.ResourceNotFoundException;
import szczepaniak.microservice.model.Employee;
import szczepaniak.microservice.repository.EmployeeRepository;


import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee create(Employee employee) {

        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee) {
        if (employeeRepository.existsById(employee.getId())) {
            return employeeRepository.save(employee);
        } else {
            throw new ResourceNotFoundException();
        }
    }


    public void delete(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Employee getById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElseThrow(EntityNotFoundException::new);
    }
    public List<Employee> getAll() {
        Iterable<Employee> usersIterable = employeeRepository.findAll();
        return  Lists.newArrayList(usersIterable);
    }
}
