package com.inn.fds.restro;

import com.inn.fds.POJO.Restro;
import com.inn.fds.dao.RestroDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RestroTest {
    @Autowired
    RestroDao rDao;
    @Test
    public void testCase(){
        Restro r = new Restro();
        assertNotNull(rDao.getAllRestro());
    }
    @Test
    public void testCase1(){
        Restro r = new Restro();
        assertNotNull(rDao.getRestroCountByEmail("test@gmail.com"));
    }

}
