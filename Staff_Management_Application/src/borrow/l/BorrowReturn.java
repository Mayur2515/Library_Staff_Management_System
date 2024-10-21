package borrow.l;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import DBConfig.DBConfig;
public class BorrowReturn {
    
    // Method to borrow a book
    public void borrowBook(int memberId, int bookId) {
        Connection conn = DBConfig.getConnection();
        String checkBookAvailability = "SELECT available FROM books WHERE id = ?";
        
        try {
            PreparedStatement checkStmt = conn.prepareStatement(checkBookAvailability);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("available")) {
                String sql = "INSERT INTO borrowed_books (member_id, book_id, borrow_date) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, memberId);
                ps.setInt(2, bookId);
                ps.setDate(3, new java.sql.Date(new Date().getTime()));
                ps.executeUpdate();

                // Update book availability
                String updateBook = "UPDATE books SET available = FALSE WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateBook);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
                
                System.out.println("Book borrowed successfully.");
            } else {
                System.out.println("Book is not available.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to return a book
    public void returnBook(int memberId, int bookId) {
        Connection conn = DBConfig.getConnection();
        String sql = "UPDATE borrowed_books SET return_date = ? WHERE member_id = ? AND book_id = ? AND return_date IS NULL";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDate(1, new java.sql.Date(new Date().getTime()));
            ps.setInt(2, memberId);
            ps.setInt(3, bookId);
            int rowsUpdated = ps.executeUpdate();
            
            if (rowsUpdated > 0) {
                String updateBook = "UPDATE books SET available = TRUE WHERE id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateBook);
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Error in returning the book.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
