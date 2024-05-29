package com.eunah.understand;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.eunah.common.JDBCTemplate.close;
import static com.eunah.common.JDBCTemplate.getConnection;

public class Application02 {
    public static void main(String[] args) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/eunah/mapper/understand-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("SalaryTop"));
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME") + " " + rset.getString("PHONE") + " " + rset.getDouble("SALARY"));
            }
            prop.loadFromXML(new FileInputStream("src/main/java/com/eunah/mapper/understand-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("MrSun"));
            rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME") + " " + rset.getString("PHONE") + " " + rset.getString("JOB_NAME"));
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }
}
