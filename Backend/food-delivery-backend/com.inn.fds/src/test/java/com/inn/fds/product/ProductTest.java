package com.inn.fds.product;

import com.inn.fds.POJO.Product;
import com.inn.fds.dao.ProductDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProductTest {
    @Autowired
    ProductDao pDao;
    @Test
    public void testCase(){
        Product p = new Product();
        assertNotNull(pDao.updateProductStatus("true",1));
    }
    @Test
    public void testCase1(){
        Product p = new Product();
        assertNotNull(pDao.getProductsByCategory(1));
    }
    @Test
    public void testCase2(){
        Product p = new Product();
        assertNotNull(pDao.getProductById(1));
    }
    @Test
    public void testCase3(){
        Product p = new Product();
        assertNotNull(pDao.getProductCountByName("Pasta"));
    }

}
