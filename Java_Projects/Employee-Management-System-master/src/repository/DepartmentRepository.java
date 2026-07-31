package repository;

import java.util.List;
import model.Department;

public interface DepartmentRepository {

    void save(Department department);

    List<Department> findAll();

    Department findById(int id);

    Department findByName(String name);

    void delete(Department department);

}