package com.eunah.section02;

import java.sql.Connection;

import static com.eunah.section02.JDBCTemplate.close;
import static com.eunah.section02.JDBCTemplate.getConnection;

public class Application01 {
    public static void main(String[] args) {
        // 메소드를 다로 만들어주고, 호출해서 연결
        Connection con = getConnection();
        System.out.println(con);
        close(con);

    }
}
