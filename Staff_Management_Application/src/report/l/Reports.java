package report.l;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import DBConfig.DBConfig;

import java.io.FileWriter;
import java.io.IOException;


public class Reports {
    public void generateReport() {
        Connection conn = DBConfig.getConnection();
        String sql = "SELECT members.name AS member_name, books.title AS book_title, borrow_date, return_date " +
                     "FROM borrowed_books " +
                     "JOIN members ON borrowed_books.member_id = members.id " +
                     "JOIN books ON borrowed_books.book_id = books.id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            FileWriter csvWriter = new FileWriter("library_report.csv");
            csvWriter.append("Member Name, Book Title, Borrow Date, Return Date\n");

            while (rs.next()) {
                csvWriter.append(rs.getString("member_name"))
                         .append(", ")
                         .append(rs.getString("book_title"))
                         .append(", ")
                         .append(rs.getString("borrow_date"))
                         .append(", ")
                         .append(rs.getString("return_date"))
                         .append("\n");
            }
            csvWriter.flush();
            csvWriter.close();
            System.out.println("Report generated: library_report.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

