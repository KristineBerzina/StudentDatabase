package com.company.Login;

import com.company.dbHelper.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static boolean signUp() {
        System.out.print("Choose a username: ");
        String username = scanner.next().trim(); // trim  - to remove spaces before or after the string
        System.out.print("Set up a password: ");
        String password = scanner.next().trim();

        try {
            ps = DbConnection.getConnection().prepareStatement("INSERT INTO users(username, password) " +
                    "VALUES('" + username + "', '" + password + "')");
            ps.execute();
            System.out.println("Successfully signed up");
            return true;
        } catch (SQLException e) {
            System.out.println("Unable to sign up. Username already exists.");
            return false;
        }

    }

    public static boolean login(){
        System.out.print("Enter your username: ");
        String username = scanner.next().trim(); // trim  - to remove spaces before or after the string
        System.out.print("Enter your password: ");
        String password = scanner.next().trim();

        try {
            ps = DbConnection.getConnection().prepareStatement("SELECT * FROM users WHERE username= '" + username + "'");
            rs = ps.executeQuery();

            String passwordCheck = "";
            while (rs.next()){
                passwordCheck = rs.getString("password");
            }
            return passwordCheck.equals(password);
        } catch (SQLException e) {
            return false;
        }

    }

}
