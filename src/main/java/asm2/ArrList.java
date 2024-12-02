/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm2;

import java.util.ArrayList;

/**
 *
 * @author vuqua
 */
public class ArrList {

    private ArrayList<Student> students;

    public ArrList() {
        students = new ArrayList<>();
    }

    // Thêm sinh viên mới vào danh sách
    public void addStudent(int id, String name, double marks) {
        Student student = new Student(id, name, marks);
        students.add(student);
    }

    // Sửa thông tin sinh viên theo ID
    public void editStudent(int id, String newName, double newMarks) {
        boolean found = false;
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(newName);
                student.setMarks(newMarks);
                System.out.println("Update student successfully.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No students to have ID: " + id);
        }
    }

    // Xóa sinh viên theo ID
    public boolean deleteStudent(int id) {
        return students.removeIf(student -> student.getId() == id);
    }

    // Tìm kiếm sinh viên theo ID
    public Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Hiển thị tất cả sinh viên
    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }

    // Bubble Sort students by marks
    public void bubleSortStudentsByMarks() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (students.get(j).getMarks() > students.get(j + 1).getMarks()) {
                 
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }
}
