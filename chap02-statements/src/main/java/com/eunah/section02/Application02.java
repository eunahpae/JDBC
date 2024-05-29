package com.eunah.section02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.eunah.common.JDBCTemplate.close;
import static com.eunah.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        // Connection, PreparedStatement 및 ResultSet 변수 초기화
        Connection con = getConnection();
        PreparedStatement pstmt = null; // SQL 문을 실행할 준비된 문 객체
        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?");
            pstmt.setString(1, "200");
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);

        }
    }
}