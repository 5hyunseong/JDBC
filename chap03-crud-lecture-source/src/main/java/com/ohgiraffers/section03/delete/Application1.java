package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {

    public static void main(String[] args) {

        Connection conn = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties props = new Properties();

        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = props.getProperty("deleteMenu");

            Scanner sc = new Scanner(System.in);

            System.out.println("삭제할 메뉴 번호(숫자)를 입력해주세요: ");
            int menuCode = sc.nextInt();

            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, menuCode);

            result = pstmt.executeUpdate(); // 설명. 쿼리 결과가 성공이면 1, 실패하면 0을 반환.

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            /* 설명. SELECT와 달리, INSERT의 경우 ResultSet은 사용하지 않음. */
            close(pstmt);
            close(conn);
        }

        if (result > 0) {
            System.out.println("메뉴 삭제 성공!");     // result가 1 일때
        } else {
            System.out.println("메뉴 삭제 실패ㅠㅠ");   // result가 0 일때
        }
    }
}
