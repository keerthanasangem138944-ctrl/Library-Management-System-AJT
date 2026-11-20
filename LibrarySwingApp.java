/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package librarymanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibrarySwingApp extends JFrame {
    private final LibraryDAO dao = new LibraryDAO();
    private DefaultTableModel tableModel;

    public LibrarySwingApp() {
        setTitle("Library Management System - AJT Swing");
        setSize(650, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Add Book", createAddBookPanel());
        tabs.addTab("Display All Books", createViewBooksPanel());
        tabs.addTab("Register Member", createRegisterMemberPanel());
        tabs.addTab("Issue Book", createIssueBookPanel());

        add(tabs);
        refreshBooksTable();
    }

    private JPanel createAddBookPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField qtyField = new JTextField();
        JButton addBtn = new JButton("Add Book");

        panel.add(new JLabel("Book Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author Name:"));
        panel.add(authorField);
        panel.add(new JLabel("Quantity:"));
        panel.add(qtyField);
        panel.add(new JLabel(""));
        panel.add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                String msg = dao.addBook(titleField.getText(), authorField.getText(), Integer.parseInt(qtyField.getText()));
                JOptionPane.showMessageDialog(this, msg);
                titleField.setText(""); authorField.setText(""); qtyField.setText("");
                refreshBooksTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number for quantity.");
            }
        });
        return panel;
    }

    private JPanel createViewBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] columns = {"Book ID", "Title", "Author", "Quantity"};
        tableModel = new DefaultTableModel(columns, 0);
        JTable table = new JTable(tableModel);

        JButton refreshBtn = new JButton("Refresh Inventory");
        refreshBtn.addActionListener(e -> refreshBooksTable());

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(refreshBtn, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshBooksTable() {
        tableModel.setRowCount(0);
        for (Object[] row : dao.getAllBooks()) {
            tableModel.addRow(row);
        }
    }

    private JPanel createRegisterMemberPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JButton regBtn = new JButton("Register Member");

        panel.add(new JLabel("Member Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel(""));
        panel.add(regBtn);

        regBtn.addActionListener(e -> {
            String msg = dao.addMember(nameField.getText(), emailField.getText(), phoneField.getText());
            JOptionPane.showMessageDialog(this, msg);
            nameField.setText(""); emailField.setText(""); phoneField.setText("");
        });
        return panel;
    }

    private JPanel createIssueBookPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField bookIdField = new JTextField();
        JTextField memberIdField = new JTextField();
        JButton issueBtn = new JButton("Issue Book");

        panel.add(new JLabel("Book ID:"));
        panel.add(bookIdField);
        panel.add(new JLabel("Member ID:"));
        panel.add(memberIdField);
        panel.add(new JLabel(""));
        panel.add(issueBtn);

        issueBtn.addActionListener(e -> {
            try {
                String msg = dao.issueBook(Integer.parseInt(bookIdField.getText()), Integer.parseInt(memberIdField.getText()));
                JOptionPane.showMessageDialog(this, msg);
                bookIdField.setText(""); memberIdField.setText("");
                refreshBooksTable();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter valid numeric IDs.");
            }
        });
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySwingApp().setVisible(true));
    }
}