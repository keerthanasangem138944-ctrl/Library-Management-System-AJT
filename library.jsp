<%-- 
    Document   : library
    Created on : 20 Nov 2026, 10:09:58 am
    Author     : sangem
--%>

<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System - Servlet & JSP</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        table { border-collapse: collapse; width: 60%; margin-bottom: 20px; }
        th, td { border: 1px solid #333; padding: 8px; text-align: left; }
        .msg { color: green; font-weight: bold; }
    </style>
</head>
<body>
    <h2>Library Management System (Servlet + JSP + JDBC)</h2>
    <% if (request.getAttribute("msg") != null) { %>
        <p class="msg"><%= request.getAttribute("msg") %></p>
    <% } %>

    <h3>Book Inventory</h3>
    <table>
        <tr><th>Book ID</th><th>Title</th><th>Author</th><th>Quantity</th></tr>
        <%
            List<Object[]> books = (List<Object[]>) request.getAttribute("bookList");
            if (books != null) {
                for (Object[] b : books) {
        %>
        <tr>
            <td><%= b[0] %></td><td><%= b[1] %></td>
            <td><%= b[2] %></td><td><%= b[3] %></td>
        </tr>
        <%      }
            } %>
    </table>

    <h3>Add New Book</h3>
    <form action="LibraryServlet" method="post">
        <input type="hidden" name="action" value="addBook">
        Title: <input type="text" name="title" required>
        Author: <input type="text" name="author" required>
        Qty: <input type="number" name="quantity" required>
        <button type="submit">Add Book</button>
    </form>

    <h3>Register Member</h3>
    <form action="LibraryServlet" method="post">
        <input type="hidden" name="action" value="addMember">
        Name: <input type="text" name="name" required>
        Email: <input type="email" name="email" required>
        Phone: <input type="text" name="phone" required>
        <button type="submit">Register</button>
    </form>

    <h3>Issue Book</h3>
    <form action="LibraryServlet" method="post">
        <input type="hidden" name="action" value="issueBook">
        Book ID: <input type="number" name="bookId" required>
        Member ID: <input type="number" name="memberId" required>
        <button type="submit">Issue Book</button>
    </form>
</body>
</html>