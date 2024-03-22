package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application6 {

    public static void main(String[] args) {


        /* 설명. Connection 생성 */
        Connection conn = getConnection();

        /* 설명. PreaparedStatement 및 ResultSet 객체 생성 */
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        /* 설명. DB 질의 결과를 받아올 DTO 및 List<EmployeeDTO> 준비 */
        EmployeeDTO row = null;
        List<EmployeeDTO> empList = null;

        /* 설명. 사용자로부터 검색 내용을 입력 받음 */
        Scanner sc = new Scanner(System.in);
        System.out.println("조회하고자 하는 이름의 성을 입력하세요: ");

        String empName = sc.nextLine();

        /* 설명. DB 정보를 가져올 Properties 객체 생성 */
        Properties props = new Properties();

        try {
            /* 설명. XML 파일로부터 SQL Query Mapping을 불러온다. */
            props.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/section02/preparedstatement/employee-query.xml"));

            /* 설명. 실행 될 쿼리를 준비(from. XML) */
            String query = props.getProperty("selectEmpByFamilyName");

            /* 설명. PreparedStatement 객체에 쿼리 싣기. */
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, empName);

            /* 설명. 쿼리 실행 후 결과집합(ResultSet) 받아오기 */
            rset = pstmt.executeQuery();

            /* 설명. 결과 집합을 반환 받을 ArrayList 생성 */
            empList = new ArrayList<>();

            while(rset.next()) {

                row = new EmployeeDTO();

                row.setEmpId(rset.getString("EMP_ID"));
                row.setEmpName(rset.getString("EMP_Name"));
                row.setEmpNo(rset.getString("EMP_No"));
                row.setEmail(rset.getString("EMAIL"));
                row.setPhone(rset.getString("PHONE"));
                row.setDeptCode(rset.getString("DEPT_CODE"));
                row.setJobCode(rset.getString("JOB_CODE"));
                row.setSalLevel(rset.getString("SAL_LEVEL"));
                row.setSalary(rset.getInt("SALARY"));
                row.setBonus(rset.getDouble("BONUS"));
                row.setManagerId(rset.getString("MANAGER_ID"));
                row.setHireDate(rset.getDate("HIRE_DATE"));
                row.setEntDate(rset.getDate("ENT_DATE"));
                row.setEntYn(rset.getString("Ent_YN"));

                /* 설명. ArrayList에 파싱한 행 추가 */
                empList.add(row);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(conn);
        }

        /* 설명. ArrayList 출력 */
//        for (EmployeeDTO emp : empList) {
//            System.out.println(emp);
//        }
        for (int i = 0; i < empList.size(); i++) {
            System.out.println(i + "번 직원: " + empList.get(i));
        }
    }
}

