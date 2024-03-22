package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application1 {

    public static void main(String[] args) {

        /* 필기. DB 접속을 위한 Connection instance 생성을 위한 래퍼런스 변수 선언(=conn) */
        /* 설명. finally 블록에서 사용하기 위해 try 블록 밖에 선언 */
        Connection conn = null;

        try {
            /* 필기. 사용할 Driver 등록 */
            Class.forName("com.mysql.cj.jdbc.Driver");

            /* 필기. DriverManager를 이용해 Connection 생성 */
            conn = DriverManager.getConnection("jdbc:mysql://localhost/employee",
                    "ohgiraffers",
                    "ohgiraffers");

            System.out.println("conn = " + conn);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
