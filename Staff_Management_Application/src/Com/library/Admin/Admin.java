package Com.library.Admin;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBConfig.DBConfig;


public class Admin {
    public void addStaff(String name, String contact, String role) {
        Connection conn = DBConfig.getConnection();
        String sql = "INSERT INTO staff (name, contact, role) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setString(3, role);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Staff member added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
