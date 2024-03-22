package com.ohgiraffers.section01.transaction;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.commit;
import static com.ohgiraffers.common.JDBCTemplate.rollback;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class Application5 {

    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        Properties prop = new Properties();
        int result1 = 0;
        int result2 = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query1 = prop.getProperty("insertCategory");
            String query2 = prop.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1, "기타");
            pstmt1.setObject(2, null);

            result1 = pstmt1.executeUpdate();

            System.out.println("result1 : " + result1);

            pstmt2 = con.prepareStatement(query2);
            pstmt2.setString(1, "개구리뒷다리통조림");
            pstmt2.setInt(2, 70000);
            pstmt2.setInt(3, 0);
            pstmt2.setString(4, "Y");

            result2 = pstmt2.executeUpdate();

            System.out.println("result2 : " + result2);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt1);
            close(pstmt2);

            if(result1 > 0 && result2 > 0) {
                System.out.println("신규 카테고리와 메뉴 등록 성공!");
                commit(con);
            } else {
                System.out.println("신규 카테고리와 메뉴 등록 실패!");
                rollback(con);
            }

            close(con);
        }

    }

}
