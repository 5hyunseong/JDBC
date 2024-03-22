package com.ohgiraffers.section02.service.model.service;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.commit;
import static com.ohgiraffers.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.ohgiraffers.section02.service.model.dao.MenuDAO;
import com.ohgiraffers.section02.service.model.dto.CategoryDTO;
import com.ohgiraffers.section02.service.model.dto.MenuDTO;

public class MenuService {

    /* 설명. 신규 메뉴 등록용 서비스 메소드 */
    public void registNewMenu() {

        /* 순서 1. Connection 생성 */
        Connection con = getConnection();

        /* 순서 2. DAO 메소드 호출 */
        MenuDAO menuDAO = new MenuDAO();

        /* 순서 2-1. 카테고리 등록 */
        CategoryDTO newCategory = new CategoryDTO();
        newCategory.setName("기타");
        newCategory.setRefCategoryCode(null);

        int result1 = menuDAO.insertNewCategory(con, newCategory);

        /* 설명. 방금 입력한 마지막 카테고리 번호 조회 */
        int newCategoryCode = menuDAO.selectLastCategoryCode(con);

        /* 순서 2-2. 메뉴 등록 */
        MenuDTO newMenu = new MenuDTO();
        newMenu.setName("메롱메롱스튜");
        newMenu.setPrice(40000);
        newMenu.setCategoryCode(newCategoryCode);
        newMenu.setOrderableStatus("Y");

        int result2 = menuDAO.insertNewMenu(con, newMenu);

        /* 순서 3. 트랜젝션 제어 */
        if(result1 > 0 && result2 > 0) {
            System.out.println("신규 카테고리와 메뉴를 추가하였습니다.");
            commit(con);
        } else {
            System.out.println("신규 카테고리와 메뉴를 추가하지 못했습니다.");
            rollback(con);
        }

        /* 순서 4. Connection 반납 */
        close(con);
    }

}
