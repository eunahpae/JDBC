package com.eunah.section01;

import java.sql.Connection; // JDBC 연결을 위한 Connection 클래스
import java.sql.ResultSet; // SQL 질의 결과 집합을 나타내는 ResultSet 클래스
import java.sql.SQLException; // SQL 예외를 처리하기 위한 SQLException 클래스
import java.sql.Statement; // SQL 문을 실행하기 위한 Statement 클래스

import static com.eunah.common.JDBCTemplate.close; // JDBCTemplate 클래스에서 close 메서드를 static 으로 임포트
import static com.eunah.common.JDBCTemplate.getConnection; // JDBCTemplate 클래스에서 getConnection 메서드를 static 으로 임포트

/* JDBC 를 사용하여 EMPLOYEE 테이블에서 EMP_ID와 EMP_NAME 을 조회하고 출력 */
public class Application01 {
    public static void main(String[] args) {
        // 데이터베이스 연결 획득
        Connection con = getConnection();

        // SQL 문을 저장하고 실행하는 인터페이스 Statement 객체
        Statement stmt = null;

        // SQL 질의 결과 집합을 받아오는 인터페이스 ResultSet 객체
        ResultSet rset = null;

        try {
            // Statement 객체 생성
            stmt = con.createStatement();

            // SQL 질의를 실행하고 결과 집합을 ResultSet에 저장
            rset = stmt.executeQuery("SELECT EMP_ID, EMP_NAME FROM EMPLOYEE");

            // ResultSet에서 데이터를 순회하면서 EMP_ID와 EMP_NAME을 출력
            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID") + " " + rset.getString("EMP_NAME"));
            }

        } catch (SQLException e) {
            // SQL 예외 발생 시 런타임 예외로 전환하여 처리
            throw new RuntimeException(e);
        } finally {
            // 연결과 Statement, ResultSet을 닫음
            close(con);
            close(stmt);
            close(rset);
        }
    }
}
