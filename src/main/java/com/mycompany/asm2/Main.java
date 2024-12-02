/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asm2;
import java.util.Scanner;
/**
 *
 * @author vuqua
 */
public class Main {
     public static void main(String[] args) {
        Stack stack = new Stack();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Edit Student by ID");
            System.out.println("4. Delete Student by ID");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Sort Students by Marks");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student marks: ");
                    double marks = scanner.nextDouble();
                    stack.addStudent(id, name, marks);
                    System.out.println("Student added successfully.");
                    break;

                case 2:
                    System.out.println("Displaying all students:");
                    stack.displayStudents();
                    break;

                case 3:
                    System.out.print("Enter student ID to edit: ");
                    int editId = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new marks: ");
                    double newMarks = scanner.nextDouble();
                    if (stack.editStudent(editId, newName, newMarks)) {
                        System.out.println("Student updated successfully.");
                    } else {
                        System.out.println("Student ID " + editId + " not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int deleteId = scanner.nextInt();
                    if (stack.deleteStudent(deleteId)) {
                        System.out.println("Student deleted successfully.");
                    } else {
                        System.out.println("Student ID " + deleteId + " not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter student ID to search: ");
                    int searchId = scanner.nextInt();
                    Node found = stack.searchStudentById(searchId);
                    if (found != null) {
                        System.out.println("Student found: " + found);
                    } else {
                        System.out.println("Student ID " + searchId + " not found.");
                    }
                    break;

                case 6:
                    System.out.println("Sorting students by marks...");
//                    stack.sortStudentsByMarks();
                    stack.sortStudentsByMarksSelectionSort();
                    System.out.println("Students sorted successfully.");
                    break;

                case 7:
                    System.out.println("Exiting the system.");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}
