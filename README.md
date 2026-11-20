# Library Management System (Advanced Java Technologies)

A multi-tier Library Management System built using **Java Swing (JDBC)**, **Java Servlets & JSP**, **Spring Framework (Maven)**, and **MySQL**.

## Project Modules
* **`LibraryManagementSystem/`** — Desktop GUI application built with Java Swing and JDBC (`com.mysql.cj.jdbc.Driver`) for book and member management[cite: 4, 7].
* **`WebApplication2/`** — Web application built with Java Servlets, JSP, and Apache Tomcat (`http://localhost:8080/WebApplication2/LibraryServlet`)[cite: 4, 7].
* **`library-management/`** — Enterprise DAO layer built with Spring Framework (`SpringConfig`, `SpringLibraryDAO`, `SpringMainApp`) and Maven (`pom.xml`)[cite: 5, 6].
* **`library_db.sql`** — Complete MySQL database schema and sample records (`library_db`)[cite: 1, 7].

## Tech Stack
* **Language:** Java (JDK 25)[cite: 3]
* **Desktop & Web:** Java Swing, Servlets, JSP, Apache Tomcat
* **Framework & Build:** Spring Framework, Apache Maven[cite: 5, 6]
* **Database:** MySQL (`library_db`)[cite: 1]

## Setup & Execution
1. Start **Apache** and **MySQL** in XAMPP, open `phpMyAdmin`, create a database named `library_db`, and import `library_db.sql`[cite: 1, 7].
2. Open the project modules in **Apache NetBeans**[cite: 3, 5].
3. Run `LibraryManagementSystem` for the desktop GUI[cite: 3, 4], deploy `WebApplication2` to Tomcat[cite: 4], or execute `SpringMainApp` in `library-management`[cite: 5, 6].
