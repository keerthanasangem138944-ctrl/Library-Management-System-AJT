/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package libraryweb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import librarymanagementsystem.LibraryDAO;

@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
    private final LibraryDAO dao = new LibraryDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("bookList", dao.getAllBooks());
        req.getRequestDispatcher("library.jsp").forward(req, resp);
    }
  
      
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        String message = "";

        if ("addBook".equals(action)) {
            message = dao.addBook(
                req.getParameter("title"),
                req.getParameter("author"),
                Integer.parseInt(req.getParameter("quantity"))
            );
        } else if ("addMember".equals(action)) {
            message = dao.addMember(
                req.getParameter("name"),
                req.getParameter("email"),
                req.getParameter("phone")
            );
        } else if ("issueBook".equals(action)) {
            message = dao.issueBook(
                Integer.parseInt(req.getParameter("bookId")),
                Integer.parseInt(req.getParameter("memberId"))
            );
        }

        req.setAttribute("msg", message);
        req.setAttribute("bookList", dao.getAllBooks());
        req.getRequestDispatcher("library.jsp").forward(req, resp);
    }
}