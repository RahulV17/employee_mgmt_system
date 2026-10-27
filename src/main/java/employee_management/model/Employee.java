package employee_management.model;

public class Employee {
    private int empId;
    private String name;
    private String email;
    private double salary;
    private int deptId;
    private String deptName; // for JOIN display

    public Employee() {}

    public Employee(int empId, String name, String email, double salary, int deptId) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.deptId = deptId;
    }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }
    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    @Override
    public String toString() {
        return "Employee{empId=" + empId + ", name='" + name + "', email='" + email +
                "', salary=" + salary + ", deptId=" + deptId +
                (deptName != null ? ", dept='" + deptName + "'" : "") + "}";
    }
}
