package com.inn.fds.userdata;

import com.inn.fds.POJO.UserData;
import com.inn.fds.dao.UserDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserDataTest {
    @Autowired
    UserDataDao udDao;
    @Test
    public void testCase(){
        UserData ud = new UserData();
        assertNotNull(udDao.getUserData(4));
    }

}
