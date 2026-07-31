package service;

import java.util.List;

import database.JdbcDepartmentRepository;
import model.Department;
import repository.DepartmentRepository;

public class DepartmentService {

    private DepartmentRepository repository;

    public DepartmentService() {
        repository = new JdbcDepartmentRepository();
    }

    public void addDepartment(Department department) {
        repository.save(department);
    }

    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Department findDepartmentById(int id) {
        return repository.findById(id);
    }

    public Department findDepartmentByName(String name) {
        return repository.findByName(name);
    }

    public void deleteDepartment(String name) {

        Department department = repository.findByName(name);

        if (department != null) {
            repository.delete(department);
        }

    }

}