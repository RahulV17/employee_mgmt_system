package employee_management;

import employee_management.model.Employee;
import employee_management.service.EmployeeService;

import java.util.Scanner;

public class EmployeeApp {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   EMPLOYEE MANAGEMENT SYSTEM (JDBC)   ");
        System.out.println("========================================");
        EmployeeService service = new EmployeeService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Enter choice: ");
            int choice;
            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Invalid input."); continue; }

            try {
                switch (choice) {
                    case 1: {
                        System.out.print("Emp ID: "); int id = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("Name: "); String name = sc.nextLine();
                        System.out.print("Email: "); String email = sc.nextLine();
                        System.out.print("Salary: "); double sal = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Dept ID: "); int did = Integer.parseInt(sc.nextLine().trim());
                        service.addEmployee(new Employee(id, name, email, sal, did));
                        break;
                    }
                    case 2: service.viewAllEmployees(); break;
                    case 3: {
                        System.out.print("Emp ID: "); int sid = Integer.parseInt(sc.nextLine().trim());
                        service.searchById(sid); break;
                    }
                    case 4: {
                        System.out.print("Emp ID to update: "); int uid = Integer.parseInt(sc.nextLine().trim());
                        System.out.print("New Name: "); String n = sc.nextLine();
                        System.out.print("New Email: "); String em = sc.nextLine();
                        System.out.print("New Salary: "); double s = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("New Dept ID: "); int d = Integer.parseInt(sc.nextLine().trim());
                        service.updateEmployee(new Employee(uid, n, em, s, d));
                        break;
                    }
                    case 5: {
                        System.out.print("Emp ID to delete: "); int did = Integer.parseInt(sc.nextLine().trim());
                        service.deleteEmployee(did); break;
                    }
                    case 6: service.viewByDepartment(); break;
                    case 7: {
                        System.out.print("Department name: "); String dn = sc.nextLine();
                        service.addDepartment(dn); break;
                    }
                    case 8: service.viewDepartments(); break;
                    case 0:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number input: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n----- MENU -----");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. View Employees Department-wise (JOIN)");
        System.out.println("7. Add Department");
        System.out.println("8. View Departments");
        System.out.println("0. Exit");
    }
}
