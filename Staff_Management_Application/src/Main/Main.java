package Main;

import Com.library.Admin.Admin;
import Library.s.Staff;
import borrow.l.BorrowReturn;
import report.l.Reports;

public class Main {
    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.addStaff("John Doe", "123456789", "Staff");

        Staff staff = new Staff();
        staff.addMember("Jane Smith", "987654321");

        BorrowReturn borrowReturn = new BorrowReturn();
        borrowReturn.borrowBook(1, 1);  // Borrow book with ID 1 by member ID 1
        borrowReturn.returnBook(1, 1);  // Return book with ID 1 by member ID 1

        Reports reports = new Reports();
        reports.generateReport();
    }
}
