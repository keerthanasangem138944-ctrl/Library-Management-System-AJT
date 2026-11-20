/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {

    public String addBook(String title, String author, int quantity) {
        String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, quantity);
            int rows = pstmt.executeUpdate();
            return rows > 0 ? "Book added successfully!" : "Failed to add book.";
        } catch (SQLException e) {
            return "Error adding book: " + e.getMessage();
        }
    }

    public List<Object[]> getAllBooks() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getInt("book_id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getInt("quantity")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String addMember(String name, String email, String phone) {
        String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);
            int rows = pstmt.executeUpdate();
            return rows > 0 ? "Member registered successfully!" : "Failed to register member.";
        } catch (SQLException e) {
            return "Error adding member: " + e.getMessage();
        }
    }

    public String issueBook(int bookId, int memberId) {
        String checkStockSql = "SELECT quantity FROM books WHERE book_id = ?";
        String issueSql = "INSERT INTO issued_books (book_id, member_id, issue_date) VALUES (?, ?, CURRENT_DATE)";
        String updateStockSql = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement checkStmt = conn.prepareStatement(checkStockSql)) {
                checkStmt.setInt(1, bookId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    if (rs.getInt("quantity") <= 0) {
                        conn.rollback();
                        return "Book is out of stock!";
                    }
                } else {
                    conn.rollback();
                    return "Book ID not found!";
                }
            }

            try (PreparedStatement issueStmt = conn.prepareStatement(issueSql)) {
                issueStmt.setInt(1, bookId);
                issueStmt.setInt(2, memberId);
                issueStmt.executeUpdate();
            }

            try (PreparedStatement updateStmt = conn.prepareStatement(updateStockSql)) {
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
            }

            conn.commit();
            return "Book issued successfully!";
        } catch (SQLException e) {
            return "Transaction Failed: " + e.getMessage();
        }
    }
}

 