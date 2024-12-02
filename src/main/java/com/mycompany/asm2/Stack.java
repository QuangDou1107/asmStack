/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.asm2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author vuqua
 */
public class Stack {

    private Node top;

    public Stack() {
        top = null;
    }

    // Add student (push into stack)
    public void addStudent(int id, String name, double marks) {
        Node newNode = new Node(id, name, marks);
        newNode.next = top;
        top = newNode;
    }

    // Edit student information
    public boolean editStudent(int id, String newName, double newMarks) {
        Node student = searchStudentById(id);
        if (student != null) {
            student.name = newName;
            student.marks = newMarks;
            student.rank = student.determineRank(newMarks); // Update rank based on new marks
            return true;
        }
        return false;
    }

    // Delete student by ID
    public boolean deleteStudent(int id) {
        if (top == null) {
            return false;
        }

        if (top.id == id) {
            top = top.next;
            return true;
        }

        Node current = top;
        while (current.next != null && current.next.id != id) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            return true;
        } else {
            return false;
        }
    }

    // Search student by ID
    public Node searchStudentById(int id) {
        Node temp = top;
        while (temp != null) {
            if (temp.id == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    // Display all students
    public void displayStudents() {
        if (top == null) {
            System.out.println("No students to display.");
            return;
        }
        Node temp = top;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // Sort students by marks using Bubble Sort algorithm
//        ArrayList<Node> list = new ArrayList<>();
//        Node temp = top;
//        while (temp != null) {
//            list.add(temp);
//            temp = temp.next;
//        }
//
//        // Sort list by marks
//        Collections.sort(list, Comparator.comparingDouble(n -> n.marks));
//
//        // Rebuild stack based on sorted list
//        top = null;
//        for (int i = list.size() - 1; i >= 0; i--) {
//            addStudent(list.get(i).id, list.get(i).name, list.get(i).marks);
//        }
//    }
    // Sort students by marks using Selection Sort algorithm
    public void sortStudentsByMarksSelectionSort() {
        ArrayList<Node> list = new ArrayList<>();
        Node temp = top;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }

        // Selection Sort list by marks
        for (int i = 0; i < list.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j).marks < list.get(minIndex).marks) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            if (minIndex != i) {
                Node tempNode = list.get(i);
                list.set(i, list.get(minIndex));
                list.set(minIndex, tempNode);
            }
        }

        // Rebuild stack based on sorted list
        top = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            addStudent(list.get(i).id, list.get(i).name, list.get(i).marks);
        }
    }

    // Binary Search students by marks
    public Node binarySearchByMarks(double targetMarks) {
        ArrayList<Node> sortedList = new ArrayList<>();
        Node temp = top;

        // Convert linked list to ArrayList
        while (temp != null) {
            sortedList.add(temp);
            temp = temp.next;
        }

        // Sort the ArrayList by marks
        sortedList.sort(Comparator.comparingDouble(n -> n.marks));

        // Binary Search implementation
        int low = 0, high = sortedList.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            Node midNode = sortedList.get(mid);

            if (midNode.marks == targetMarks) {
                return midNode; // Found the node with the target marks
            } else if (midNode.marks < targetMarks) {
                low = mid + 1; // Move to the right half
            } else {
                high = mid - 1; // Move to the left half
            }
        }

        return null; // No student found with the target marks
    }

    // Search students by name (Linear Search)
    public ArrayList<Node> searchStudentByName(String name) {
        ArrayList<Node> matchingStudents = new ArrayList<>();
        Node temp = top;

        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                matchingStudents.add(temp);
            }
            temp = temp.next;
        }

        return matchingStudents;
    }

    public void sortStudentsByMarks() {
        ArrayList<Node> list = new ArrayList<>();
        Node temp = top;
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }

        // Bubble Sort list by marks
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).marks > list.get(j + 1).marks) {
                    // Swap the nodes if they are in the wrong order
                    Node tempNode = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tempNode);
                }
            }
        }

        // Rebuild stack based on sorted list
        top = null;
        for (int i = list.size() - 1; i >= 0; i--) {
            addStudent(list.get(i).id, list.get(i).name, list.get(i).marks);
        }
    }
}
