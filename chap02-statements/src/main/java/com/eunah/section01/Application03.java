package com.eunah.section01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.eunah.common.JDBCTemplate.close;
import static com.eunah.common.JDBCTemplate.getConnection;

public class Application03 {
    public static void main(String[] args) {

        Connection con = getConnection();

        Statement stmt = null;
        ResultSet rset = null;

        try {
            stmt = con.createStatement();
            System.out.println("조회하고자 하는 이름을 입력해주세요. : ");

            // 스캐너로 입력받아서 처리
            Scanner sc = new Scanner(System.in);
            String empName = sc.nextLine();
            String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_NAME = '" + empName + "'";

            rset = stmt.executeQuery(query);

            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }
    }
}
