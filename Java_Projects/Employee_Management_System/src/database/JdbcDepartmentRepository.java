package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Department;
import repository.DepartmentRepository;

public class JdbcDepartmentRepository implements DepartmentRepository {

    @Override
    public void save(Department department) {

        String sql = "INSERT INTO departments(name) VALUES(?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, department.getName());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Department> findAll() {

        List<Department> departments = new ArrayList<>();

        String sql = "SELECT * FROM departments";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Department department = new Department(
                        rs.getInt("id"),
                        rs.getString("name"));

                departments.add(department);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departments;

    }

    @Override
    public Department findById(int id) {

        String sql = "SELECT * FROM departments WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Department(
                        rs.getInt("id"),
                        rs.getString("name"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public Department findByName(String name) {

        String sql = "SELECT * FROM departments WHERE name = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Department(
                        rs.getInt("id"),
                        rs.getString("name"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    public void delete(Department department) {

        String sql = "DELETE FROM departments WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, department.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}