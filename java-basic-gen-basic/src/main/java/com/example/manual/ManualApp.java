package com.example.manual;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManualApp {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:8521:DXCORA19C";
        String user = "DEMO";
        String password = "DEMO";
        String query = "SELECT MAX(NAME) FROM PRODUCT";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                FileWriter writer = new FileWriter("product_manual.txt")) {

            if (rs.next()) {
                String productName = rs.getString(1);
                writer.write("Max Product Name: " + productName);
                System.out.println("Oracle Data Saved: " + productName);
            }
        } catch (SQLException | java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
