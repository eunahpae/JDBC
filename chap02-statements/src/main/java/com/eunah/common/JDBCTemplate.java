package com.eunah.common;

import java.io.FileReader; // 파일을 읽기 위한 FileReader 클래스
import java.io.IOException;// 입출력 예외를 처리하기 위한 IOException 클래스
import java.sql.*; // JDBC 관련 클래스들을 포함하는 java.sql 패키지
import java.util.Properties; // 속성을 다루기 위한 Properties 클래스


/* JDBC 연결 및 자원 관리를 위한 유틸리티 클래스 */
public class JDBCTemplate {

    /* 데이터베이스 연결을 반환
       @return Connection 객체 */
    public static Connection getConnection() {
        Connection con = null; // 연결 객체 선언 및 초기화
        Properties prop = new Properties(); // 속성 객체 선언

        try {
            // 속성 파일로부터 URL을 읽어와 설정
            prop.load(new FileReader("src/main/java/com/eunah/config/connection-info.properties"));
            String url = prop.getProperty("url"); // URL 속성 값 가져오기

            // DriverManager를 사용하여 데이터베이스에 연결
            con = DriverManager.getConnection(url, prop);

        } catch (IOException e) {
            // 파일 읽기 예외 처리
            throw new RuntimeException(e);
        } catch (SQLException e) {
            // 데이터베이스 연결 예외 처리
            throw new RuntimeException(e);
        }

        return con; // 연결 객체 반환
    }

    /* Connection 객체 닫기
       @param con Connection 객체 */
    public static void close(Connection con) {
        try {
            con.close(); // Connection 닫기
        } catch (SQLException e) {
            // 데이터베이스 연결 닫기 예외 처리
            throw new RuntimeException(e);
        }
    }

    /* Statement 객체 닫기
       @param stmt Statement 객체 */
    public static void close(Statement stmt) {
        try {
            stmt.close(); // Statement 닫기
        } catch (SQLException e) {
            // SQL 문 실행 객체 닫기 예외 처리
            throw new RuntimeException(e);
        }
    }

    /* ResultSet 객체 닫기
       @param rset ResultSet 객체 */
    public static void close(ResultSet rset) {
        try {
            rset.close(); // ResultSet 닫기
        } catch (SQLException e) {
            // 결과 집합 닫기 예외 처리
            throw new RuntimeException(e);
        }
    }
}
