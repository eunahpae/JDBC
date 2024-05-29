package com.eunah.section02;

import java.sql.*;

import static com.eunah.common.JDBCTemplate.close;
import static com.eunah.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null; // 쿼리문을 미완성으로도 사용할 수 있음

        ResultSet rset = null;

        try {
            pstmt = con.prepareStatement("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString(1) + " " + rset.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(rset);
            close(pstmt);
        }


    }
}
