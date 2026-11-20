package librarymanagementsystem;

import java.util.Scanner;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        LibraryDAO dao = new LibraryDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n========== LIBRARY SYSTEM (XAMPP + JDBC) ==========");
            System.out.println("1. Add New Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Register Member");
            System.out.println("4. Issue Book");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author Name: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = scanner.nextInt();
                    dao.addBook(title, author, qty);
                    break;

                case 2:
                    dao.displayBooks();
                    break;

                case 3:
                    System.out.print("Enter Member Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();
                    dao.addMember(name, email, phone);
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    int bId = scanner.nextInt();
                    System.out.print("Enter Member ID: ");
                    int mId = scanner.nextInt();
                    dao.issueBook(bId, mId);
                    break;

                case 5:
                    System.out.println("Exiting System...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Try again!");
            }
        }
    }
}