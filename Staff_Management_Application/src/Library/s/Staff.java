package Library.s;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import DBConfig.DBConfig;


public class Staff {
    public void addMember(String name, String contact) {
        Connection conn = DBConfig.getConnection();
        String barcode = UUID.randomUUID().toString();  // Generate unique barcode
        String sql = "INSERT INTO members (name, contact, barcode) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, contact);
            ps.setString(3, barcode);
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Member added successfully with barcode: " + barcode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
