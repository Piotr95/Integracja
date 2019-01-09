package szczepaniak.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import szczepaniak.microservice.model.Employee;
import szczepaniak.microservice.repository.EmployeeRepository;
import szczepaniak.microservice.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static szczepaniak.microservice.model.JobPosition.JUNIOR_DEV;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmpoloyeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;


    @Test
    public void get() {
        given(employeeRepository.findById(1L)).willReturn(Optional.of(createTestEmployee(1L)));

        Employee employee = employeeService.getById(1L);

        assertThat(employee.getId(), is(1L));
        assertThat(employee.getFirstName(), is("Employee no 1"));

        verify(employeeRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(employeeRepository);
    }

    private Employee createTestEmployee(Long id) {
        Employee employee = new Employee();

        employee.setId(id);
        employee.setFirstName("Employee no " + id.toString());
        employee.setLastName("Jones no "+ id.toString());
        employee.setJobPosition(JUNIOR_DEV);
        return employee;
    }

    @Test
    public void getAll() {
        given(employeeRepository.findAll()).willReturn(createTestEmployeesList());

        List<Employee> employees = employeeService.getAll();

        assertTrue(employees.size() > 1);

        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);
    }

    private Iterable<Employee> createTestEmployeesList() {
        List<Employee> employees = new ArrayList<>();

        for(Long i = 0L; i < 10L; i++)
            employees.add(createTestEmployee(i));

        return employees;
    }

    @Test

    public void create() {
        given(employeeRepository.save(any())).willReturn(createTestEmployee(3L));

        Employee employee = employeeService.create(createTestEmployee(3L));

        assertThat(employee.getId(), is(3L));
        assertThat(employee.getFirstName(), is("Employee no 3"));
        assertThat(employee.getLastName(), is("Jones no 3"));
        assertThat(employee.getJobPosition(), is(JUNIOR_DEV));

        verify(employeeRepository, times(1)).save(any());
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test

    public void update() {
        given(employeeRepository.existsById(any())).willReturn(true);
        given(employeeRepository.save(any())).willAnswer(a -> a.getArgument(0));

        Employee employee = createTestEmployee(2L);
        employee.setFirstName("Employee no 4");
        Employee updatedEmployee = employeeService.update(employee);

        assertThat(updatedEmployee.getId(), is(2L));
        assertThat(updatedEmployee.getFirstName(), is("Employee no 4"));

        verify(employeeRepository, times(1)).save(any());
        verify(employeeRepository, times(1)).existsById(any());
        verifyNoMoreInteractions(employeeRepository);
    }
}



