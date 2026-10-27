package employee_management.service;

import employee_management.dao.EmployeeDAO;
import employee_management.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO dao = new EmployeeDAO();

    public void addEmployee(Employee e) {
        try { dao.addEmployee(e); }
        catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }

    public void viewAllEmployees() {
        try {
            List<Employee> list = dao.getAllEmployees();
            if (list.isEmpty()) { System.out.println("No employees found."); return; }
            System.out.printf("%-6s %-18s %-22s %-10s %-6s %-12s%n", "ID", "NAME", "EMAIL", "SALARY", "DEPT", "DEPT_NAME");
            System.out.println("--------------------------------------------------------------------------");
            for (Employee e : list) {
                System.out.printf("%-6d %-18s %-22s %-10.2f %-6d %-12s%n",
                        e.getEmpId(), e.getName(), e.getEmail(), e.getSalary(), e.getDeptId(),
                        e.getDeptName() == null ? "-" : e.getDeptName());
            }
        } catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }

    public void searchById(int id) {
        try {
            Employee e = dao.getEmployeeById(id);
            if (e == null) System.out.println("No employee found with id " + id);
            else System.out.println(e);
        } catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }

    public void updateEmployee(Employee e) {
        try { dao.updateEmployee(e); }
        catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }

    public void deleteEmployee(int id) {
        try { dao.deleteEmployee(id); }
        catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }

    public void addDepartment(String name) {
        try { dao.addDepartment(name); }
        catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }

    public void viewDepartments() {
        try {
            List<String> list = dao.getAllDepartments();
            if (list.isEmpty()) { System.out.println("No departments."); return; }
            for (String d : list) System.out.println(d);
        } catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }

    public void viewByDepartment() {
        try {
            List<Employee> list = dao.getEmployeesByDepartment();
            if (list.isEmpty()) { System.out.println("No employees found."); return; }
            System.out.println("---- Employees Department-wise (JOIN) ----");
            String current = null;
            for (Employee e : list) {
                if (!e.getDeptName().equals(current)) {
                    current = e.getDeptName();
                    System.out.println("\n[ " + current + " ]");
                }
                System.out.printf("   %-6d %-18s %-22s %-10.2f%n", e.getEmpId(), e.getName(), e.getEmail(), e.getSalary());
            }
        } catch (SQLException ex) { System.out.println("DB error: " + ex.getMessage()); }
    }
}
