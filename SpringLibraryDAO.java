package com.ajt.springlibrary;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SpringLibraryDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addBook(String title, String author, int quantity) {
        String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, title, author, quantity);
        System.out.println("Book added via Spring JdbcTemplate: " + title);
    }

    public void addMember(String name, String email, String phone) {
        String sql = "INSERT INTO members (name, email, phone) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, name, email, phone);
        System.out.println("Member registered via Spring JdbcTemplate: " + name);
    }

     public List getAllBooks() {
        return jdbcTemplate.queryForList("SELECT * FROM books");
    }

    public void issueBook(int bookId, int memberId) {
        Integer qty = jdbcTemplate.queryForObject(
                "SELECT quantity FROM books WHERE book_id = ?", Integer.class, bookId);
        if (qty == null || qty <= 0) {
            System.out.println("Book ID " + bookId + " is out of stock!");
            return;
        }
        jdbcTemplate.update(
                "INSERT INTO issued_books (book_id, member_id, issue_date) VALUES (?, ?, CURRENT_DATE)",
                bookId, memberId);
        jdbcTemplate.update("UPDATE books SET quantity = quantity - 1 WHERE book_id = ?", bookId);
        System.out.println("Book ID " + bookId + " issued to Member ID " + memberId + " via Spring!");
    }

    public void returnBook(int issueId) {
        Integer bookId = jdbcTemplate.queryForObject(
                "SELECT book_id FROM issued_books WHERE issue_id = ? AND return_date IS NULL",
                Integer.class, issueId);
        if (bookId == null) {
            System.out.println("Invalid or already returned Issue ID: " + issueId);
            return;
        }
        jdbcTemplate.update(
                "UPDATE issued_books SET return_date = CURRENT_DATE WHERE issue_id = ?", issueId);
        jdbcTemplate.update("UPDATE books SET quantity = quantity + 1 WHERE book_id = ?", bookId);
        System.out.println("Issue ID " + issueId + " returned successfully via Spring!");
    }
}