package employee_management.dao;

import employee_management.model.Employee;
import employee_management.util.EmployeeDBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee e) throws SQLException {
        String sql = "INSERT INTO employees (emp_id, name, email, salary, dept_id) VALUES (?,?,?,?,?)";
        try (Connection c = EmployeeDBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, e.getEmpId());
            ps.setString(2, e.getName());
            ps.setString(3, e.getEmail());
            ps.setDouble(4, e.getSalary());
            ps.setInt(5, e.getDeptId());
            int r = ps.executeUpdate();
            System.out.println(r > 0 ? "Employee added successfully." : "Failed to add employee.");
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT e.emp_id, e.name, e.email, e.salary, e.dept_id, d.dept_name " +
                "FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id";
        try (Connection c = EmployeeDBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setSalary(rs.getDouble("salary"));
                e.setDeptId(rs.getInt("dept_id"));
                e.setDeptName(rs.getString("dept_name"));
                list.add(e);
            }
        }
        return list;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        String sql = "SELECT e.emp_id, e.name, e.email, e.salary, e.dept_id, d.dept_name " +
                "FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id WHERE e.emp_id = ?";
        try (Connection c = EmployeeDBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee e = new Employee();
                    e.setEmpId(rs.getInt("emp_id"));
                    e.setName(rs.getString("name"));
                    e.setEmail(rs.getString("email"));
                    e.setSalary(rs.getDouble("salary"));
                    e.setDeptId(rs.getInt("dept_id"));
                    e.setDeptName(rs.getString("dept_name"));
                    return e;
                }
            }
        }
        return null;
    }

    public void updateEmployee(Employee e) throws SQLException {
        String sql = "UPDATE employees SET name=?, email=?, salary=?, dept_id=? WHERE emp_id=?";
        try (Connection c = EmployeeDBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setString(2, e.getEmail());
            ps.setDouble(3, e.getSalary());
            ps.setInt(4, e.getDeptId());
            ps.setInt(5, e.getEmpId());
            int r = ps.executeUpdate();
            System.out.println(r > 0 ? "Employee updated successfully." : "No employee found with id " + e.getEmpId());
        }
    }

    public void deleteEmployee(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE emp_id = ?";
        try (Connection c = EmployeeDBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int r = ps.executeUpdate();
            System.out.println(r > 0 ? "Employee deleted successfully." : "No employee found with id " + id);
        }
    }

    public void addDepartment(String name) throws SQLException {
        String sql = "INSERT INTO departments (dept_name) VALUES (?)";
        try (Connection c = EmployeeDBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, name);
            int r = ps.executeUpdate();
            System.out.println(r > 0 ? "Department added successfully." : "Failed to add department.");
        }
    }

    public List<String> getAllDepartments() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT dept_id, dept_name FROM departments";
        try (Connection c = EmployeeDBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) list.add(rs.getInt("dept_id") + " - " + rs.getString("dept_name"));
        }
        return list;
    }

    public List<Employee> getEmployeesByDepartment() throws SQLException {
        List<Employee> list = new ArrayList<>();
        // Statement for a fixed JOIN query (department-wise grouping via ORDER BY)
        String sql = "SELECT e.emp_id, e.name, e.email, e.salary, d.dept_name " +
                "FROM employees e JOIN departments d ON e.dept_id = d.dept_id " +
                "ORDER BY d.dept_name, e.name";
        try (Connection c = EmployeeDBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = new Employee();
                e.setEmpId(rs.getInt("emp_id"));
                e.setName(rs.getString("name"));
                e.setEmail(rs.getString("email"));
                e.setSalary(rs.getDouble("salary"));
                e.setDeptName(rs.getString("dept_name"));
                list.add(e);
            }
        }
        return list;
    }
}
