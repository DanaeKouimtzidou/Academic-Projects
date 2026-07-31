package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import model.Employee;
import repository.EmployeeRepository;

public class JdbcEmployeeRepository implements EmployeeRepository {

	@Override
    public void save(Employee employee) {

        String sql = "INSERT INTO employees(first_name,last_name,email,salary,age,department_id) VALUES(?,?,?,?,?,?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());
            ps.setDouble(4, employee.getSalary());
            ps.setInt(5, employee.getAge());
            ps.setInt(6, employee.getDepartment().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Employee> findAll() {

        List<Employee> employees = new ArrayList<>();

        String sql =
                "SELECT e.*, d.id AS dep_id, d.name AS dep_name " +
                "FROM employees e " +
                "JOIN departments d ON e.department_id = d.id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Department department = new Department(
                        rs.getInt("dep_id"),
                        rs.getString("dep_name")
                );

                Employee employee = new Employee(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getInt("age"),
                        department
                );

                employees.add(employee);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;

    }

    @Override
    public Employee findById(int id) {

        String sql =
                "SELECT e.*, d.id AS dep_id, d.name AS dep_name " +
                "FROM employees e " +
                "JOIN departments d ON e.department_id = d.id " +
                "WHERE e.id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Department department = new Department(
                        rs.getInt("dep_id"),
                        rs.getString("dep_name")
                );

                return new Employee(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getDouble("salary"),
                        rs.getInt("age"),
                        department
                );

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }


    @Override
    public void delete(Employee employee) {

        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employee.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    
    @Override
    public void updateSalary(int id, double salary) {

        String sql = "UPDATE employees SET salary = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}