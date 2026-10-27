package employee_management.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class EmployeeDBConnection {
    private static final Properties properties = new Properties();

    static {
        try (InputStream is = EmployeeDBConnection.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (is != null) properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("employee.db.url"),
                properties.getProperty("employee.db.usr").trim(),
                properties.getProperty("employee.db.pwd").trim());
    }
}
