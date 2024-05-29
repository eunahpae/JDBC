package com.eunah.understand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.eunah.common.JDBCTemplate.close;
import static com.eunah.common.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query1 = "SELECT EMP_ID,EMP_NAME,PHONE,SALARY FROM EMPLOYEE WHERE SALARY = (SELECT MAX(SALARY) FROM EMPLOYEE);";
        String query2 = "SELECT E.EMP_ID,E.EMP_NAME,E.PHONE,J.JOB_NAME FROM EMPLOYEE E JOIN JOB J ON E.JOB_CODE=J.JOB_CODE WHERE EMP_NAME ='선동일';";

        try {
            pstmt = con.prepareStatement(query1);
            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME") + " " + rset.getString("PHONE") + " " + rset.getDouble("SALARY"));
            }

            pstmt = con.prepareStatement(query2);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME") + " " + rset.getString("PHONE") + " " + rset.getString("JOB_NAME"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }
}
