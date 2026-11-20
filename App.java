package com.library;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        // Load configuration and build SessionFactory
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(BookIssue.class);
        
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        // Create a new Book Issue record
        BookIssue issue = new BookIssue("Effective Java", "Sangem", "2026-11-05");
        
        // Save record to database
        session.persist(issue);
        tx.commit();

        System.out.println("Book issued successfully! Generated ID: " + issue.getIssueId());

        session.close();
        factory.close();
    }
}