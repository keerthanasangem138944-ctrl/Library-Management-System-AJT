# Library Management System (Advanced Java Technologies)

A multi-tier Library Management System built using **Java Swing (JDBC)**, **Java Servlets & JSP**, **Spring Framework (Maven)**, and **MySQL**.

## Project Modules
* **`LibraryManagementSystem/`** — Desktop GUI application built with Java Swing and JDBC (`com.mysql.cj.jdbc.Driver`) for book and member management.
* **`WebApplication2/`** — Web application built with Java Servlets, JSP, and Apache Tomcat (`http://localhost:8080/WebApplication2/LibraryServlet`).
* **`library-management/`** — Enterprise DAO layer built with Spring Framework (`SpringConfig`, `SpringLibraryDAO`, `SpringMainApp`) and Maven (`pom.xml`).
* **`library_db.sql`** — Complete MySQL database schema and sample records (`library_db`).

## Tech Stack
* **Language:** Java (JDK 25)
* **Desktop & Web:** Java Swing, Servlets, JSP, Apache Tomcat
* **Framework & Build:** Spring Framework, Apache Maven
* **Database:** MySQL (`library_db`)

## Setup & Execution
1. Start **Apache** and **MySQL** in XAMPP, open `phpMyAdmin`, create a database named `library_db`, and import `library_db.sql`.
2. Open the project modules in **Apache NetBeans**.
3. Run `LibraryManagementSystem` for the desktop GUI, deploy `WebApplication2` to Tomcat, or execute `SpringMainApp` in `library-management`.
