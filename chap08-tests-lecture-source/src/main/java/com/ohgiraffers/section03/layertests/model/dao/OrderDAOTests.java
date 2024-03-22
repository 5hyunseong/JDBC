package com.ohgiraffers.section03.layertests.model.dao;

/*
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
 */
import com.ohgiraffers.common.JDBCTemplate;
import com.ohgiraffers.section03.layertests.model.dto.CategoryDTO;
import com.ohgiraffers.section03.layertests.model.dto.OrderDTO;

        import java.sql.Connection;
import java.util.List;
/*
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
*/
public class OrderDAOTests {

    private Connection con;
    private OrderDAO orderDAO;
    private OrderDTO order;

//    @Before
    public void setup() {
        con = JDBCTemplate.getConnection();
        orderDAO = new OrderDAO();

        order = new OrderDTO();
        order.setDate("20/12/31");
        order.setTime("12:35:41");
        order.setTotalOrderPrice(30000);
    }

//    @Test
//    @Ignore
    public void testSelectAllCategory() {

        List<CategoryDTO> categoryList = orderDAO.selectAllCategory(con);

//        assertNotNull(categoryList);
    }

//    @Test
    public void testInsertOrder() {

        int result = orderDAO.insertOrder(con, order);

//        assertEquals(1, result);
    }

}
