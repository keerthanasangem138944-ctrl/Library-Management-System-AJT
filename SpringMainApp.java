/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ajt.springlibrary;

import java.util.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringMainApp {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringConfig.class)) {

            SpringLibraryDAO dao = context.getBean(SpringLibraryDAO.class);

            dao.addBook("Spring in Action", "Craig Walls", 4);
            dao.addMember("Kiran Desai", "kiran.desai2026@example.com", "9824011223");
            dao.issueBook(2, 1);

            System.out.println("\n=== BOOK INVENTORY (SPRING JDBC) ===");
            for (Object obj : dao.getAllBooks()) {
    Map<String, Object> book = (Map<String, Object>) obj;
                System.out.println(
                    "ID: " + book.get("book_id")
                    + " | Title: " + book.get("title")
                    + " | Author: " + book.get("author")
                    + " | Qty: " + book.get("quantity")
                );
            }
        }
    }
}