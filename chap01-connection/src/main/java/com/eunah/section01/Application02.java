package com.eunah.section01;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {
    public static void main(String[] args) {
        // 변수로 선언하여 대입
        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "songpa";
        String password = "songpa";

        Connection con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
