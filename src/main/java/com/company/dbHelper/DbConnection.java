package com.company.dbHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "Betmens_07");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}
