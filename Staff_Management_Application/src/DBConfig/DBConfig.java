package DBConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static final String URL = "jdbc:mysql://localhost:3306/hiplab";
    private static final String USER = "root";
    private static final String PASSWORD = "Choper@02";

    // Establish the database connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // The driver is automatically loaded from the classpath.
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hiplab", "root", "Choper@02");
            System.out.println("Database is connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
