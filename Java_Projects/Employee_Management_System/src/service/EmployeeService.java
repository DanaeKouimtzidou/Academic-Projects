package service;

import java.util.List;

import database.JdbcEmployeeRepository;
import model.Employee;
import repository.EmployeeRepository;

public class EmployeeService {

    private EmployeeRepository repository;

    public EmployeeService() {
        repository = new JdbcEmployeeRepository();
    }

    public void addEmployee(Employee employee) {
        repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee findEmployeeById(int id) {
        return repository.findById(id);
    }

   
    public void deleteEmployee(int id) {

        Employee employee = repository.findById(id);

        if (employee != null) {
            repository.delete(employee);
        }

    }

    public void updateSalary(int id, double salary) {
        repository.updateSalary(id, salary);
    }

}