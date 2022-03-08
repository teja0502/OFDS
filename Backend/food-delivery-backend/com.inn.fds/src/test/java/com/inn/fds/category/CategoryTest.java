package com.inn.fds.category;

import com.inn.fds.POJO.Category;
import com.inn.fds.dao.CategoryDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
@SpringBootTest
public class CategoryTest {
    @Autowired
    CategoryDao cDao;
    @Test
    public void testCase(){
        Category c = new Category();
        assertNotNull(cDao.getCountByRestroAndCategory(1,"Pasta"));
    }
    @Test
    public void testCase1(){
        Category c = new Category();
        assertNotNull(cDao.getCategoryByRestro(1));
    }
    @Test
    public void testCase2(){
        Category c = new Category();
        assertNotNull(cDao.updateCategory(1,"Pasta"));
    }
}
