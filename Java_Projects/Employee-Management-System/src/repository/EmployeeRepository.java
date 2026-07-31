package repository;

import java.util.List;

import model.Employee;

public interface EmployeeRepository {

    void save(Employee employee);

    List<Employee> findAll();

    Employee findById(int id);

    void delete(Employee employee);

    void updateSalary(int id, double salary);

}