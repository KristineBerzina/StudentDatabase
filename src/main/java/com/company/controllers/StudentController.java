package com.company.controllers;

import com.company.dbHelper.DbConnection;
import com.company.objects.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentController {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static boolean addNewStudent() {
        System.out.print("Enter the name of the student: ");
        String name = scanner.next();
        System.out.print("Enter the age of the student: ");
        int age = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO students(name, age) " +
                    "VALUES('" + name + "', " + age + ")");
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static Student getStudentById() {
        System.out.print("Enter the ID of the student: ");
        int id = scanner.nextInt();

        Student student = new Student();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM students WHERE id=" + id);
            rs = ps.executeQuery();

            int studentId, age;
            String name;

            while (rs.next()) {
                studentId = rs.getInt("id");
                name = rs.getString("name");
                age = rs.getInt("age");
                student.setId(studentId);
                student.setName(name);
                student.setAge(age);
            }

            return student;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void addScores() {
        System.out.print("Enter the student's ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter the Mathematics score: ");
        int mathScore = scanner.nextInt();
        System.out.print("Enter the English score: ");
        int engScore = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO scores(mathematics, english, student_id) " +
                    "VALUES(" + mathScore + ", " + engScore + "," + studentId + ")");
            ps.execute();
            System.out.println("Successfully added scores");

        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void removeStudent() {
        System.out.print("Enter the ID of the student you wish to remove: ");
        int id = scanner.nextInt();

        try {
            ps = DbConnection.getConnection().prepareStatement("DELETE FROM scores WHERE student_id=" + id);
            ps.execute();
            ps = DbConnection.getConnection().prepareStatement("DELETE FROM students WHERE id=" + id);
            ps.execute();

            System.out.println("Student removed successfully");

        } catch (SQLException e) {
            System.out.println("Could not remove student. Try again!");
            e.printStackTrace();
        }
    }

    public static void editStudent() {
        System.out.print("Enter the ID of the student whose details you wish to edit: ");
        int id = scanner.nextInt();
        System.out.print("Do you wish to edit student's name? Print 1 for yes or 2 for no: ");
        int answer = scanner.nextInt();

        if (answer == 1) {
            System.out.print("Please insert the edited student's name: ");
            String newName = scanner.next();
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE students SET name='" + newName + "'WHERE id=" + id);
                ps.execute();

                System.out.println("Student's name edited successfully");

            } catch (SQLException e) {
                System.out.println("Could not edit name. Try again!");
                e.printStackTrace();
            }
        }

        System.out.print("Do you wish to edit student's age? Print 1 for yes or 2 for no: ");
        answer = scanner.nextInt();
        if (answer == 1) {
            System.out.print("Please insert the edited student's age: ");
            int newAge = scanner.nextInt();
            try {
                ps = DbConnection.getConnection().prepareStatement("UPDATE students SET age='" + newAge + "'WHERE id=" + id);
                ps.execute();

                System.out.println("Student's age edited successfully");

            } catch (SQLException e) {
                System.out.println("Could not edit age. Try again!");
                e.printStackTrace();
            }
        }
    }

}
