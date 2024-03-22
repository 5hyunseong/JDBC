package com.ohgiraffers.section01.insert;

import com.ohgiraffers.model.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {

    public static void main(String[] args) {

        /* 설명. 추가하고자 하는 메뉴의 정보를 사용자로부터 입력 받는다. */
        Scanner sc = new Scanner(System.in);

        System.out.println("메뉴의 이름을 입력해주세요: ");
        String menuName = sc.nextLine();

        System.out.println("메뉴의 가격(숫자)을 입력해주세요: ");
        int menuPrice = sc.nextInt();

        System.out.println("메뉴가 속할 카테고리 코드를 입력하세요: ");
        int categoryCode = sc.nextInt();

        System.out.println("메뉴의 판매 여부를 결정해주세요(Y/N): ");
        sc.nextLine();
        String orderableStatus = sc.nextLine().toUpperCase();   // 설명. 어떤 문자가 들어오던 대문자로 변환

        /* 설명. 조립할 메뉴 DTO 생성*/
        MenuDTO newMenu = new MenuDTO();

        newMenu.setName(menuName);
        newMenu.setPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(orderableStatus);
        /* 설명. 여기까지 사용자로부터 입력받은 정보로 Menu DTO를 조립했다.
         *  즉, DTO를 가지고 쿼리를 수행할 준비가 되었다.
        * */

        Connection conn = getConnection();

        PreparedStatement pstmt = null;
        int result = 0;

        Properties props = new Properties();

        try {
            props.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = props.getProperty("insertMenu");

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newMenu.getName());
            pstmt.setInt(2, newMenu.getPrice());
            pstmt.setInt(3, newMenu.getCategoryCode());
            pstmt.setString(4, newMenu.getOrderableStatus());

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
            System.out.println("메뉴 등록 성공!");     // result가 1 일때
        } else {
            System.out.println("메뉴 등록 실패ㅠㅠ");   // result가 0 일때
        }
    }
}