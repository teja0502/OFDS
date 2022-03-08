package com.inn.fds.appuser;


import com.inn.fds.dao.AppUserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppUserTest {

    @Autowired
    AppUserDao aUser;
    @Test
    public void testcase(){
        com.inn.fds.POJO.AppUser a = new com.inn.fds.POJO.AppUser();
        a.setId(5);
        a.setEmail("food@gmail.com");
        a.setRole("user");
        a.setPassword("case");
        aUser.save(a);
        assertNotNull(aUser.findByEmailId("food@gmail.com").getEmail());
    }

    @Test
    public void testCase1(){
        com.inn.fds.POJO.AppUser a = new com.inn.fds.POJO.AppUser();
        assertNotNull(aUser.getUserByUsernameAndPassword("admin@gmail.com","admin"));
    }



}
