/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asm2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementGUI extends JFrame {
    private Stack stack;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public StudentManagementGUI() {
        stack = new Stack();

        // Setup JFrame
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table for displaying students
        String[] columnNames = {"ID", "Name", "Marks", "Rank"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("Add Student");
        JButton editButton = new JButton("Edit Student");
        JButton deleteButton = new JButton("Delete Student");
        JButton searchButton = new JButton("Search Student");
        JButton sortButton = new JButton("Sort Students");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Action listeners for buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStudent();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortStudents();
            }
        });
    }

    private void addStudent() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField marksField = new JTextField();
        Object[] fields = {
            "ID:", idField,
            "Name:", nameField,
            "Marks:", marksField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Add Student", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            double marks = Double.parseDouble(marksField.getText());
            stack.addStudent(id, name, marks);
            refreshTable();
        }
    }

    private void editStudent() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Student ID to Edit:");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            JTextField nameField = new JTextField();
            JTextField marksField = new JTextField();
            Object[] fields = {
                "New Name:", nameField,
                "New Marks:", marksField
            };

            int option = JOptionPane.showConfirmDialog(this, fields, "Edit Student", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String newName = nameField.getText();
                double newMarks = Double.parseDouble(marksField.getText());
                if (stack.editStudent(id, newName, newMarks)) {
                    refreshTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Student ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void deleteStudent() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Student ID to Delete:");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            if (stack.deleteStudent(id)) {
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchStudent() {
        String idStr = JOptionPane.showInputDialog(this, "Enter Student ID to Search:");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            Node student = stack.searchStudentById(id);
            if (student != null) {
                JOptionPane.showMessageDialog(this, student.toString(), "Student Found", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Student ID not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sortStudents() {
        stack.sortStudentsByMarksSelectionSort();
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0); // Clear table
        Node temp = stack.searchStudentById(0); // Reset iteration
        while (temp != null) {
            Object[] row = {temp.id, temp.name, temp.marks, temp.rank};
            tableModel.addRow(row);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementGUI gui = new StudentManagementGUI();
            gui.setVisible(true);
        });
    }
}
