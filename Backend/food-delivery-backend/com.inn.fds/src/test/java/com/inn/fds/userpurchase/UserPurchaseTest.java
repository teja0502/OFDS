package com.inn.fds.userpurchase;

import com.inn.fds.POJO.UserPurchase;
import com.inn.fds.dao.UserPurchaseDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserPurchaseTest {
    @Autowired
    UserPurchaseDao upDao;
    @Test
    public void testCase(){
        UserPurchase up = new UserPurchase();
        assertNotNull(upDao.getPurchaseRecordByUserId(3));
    }
    @Test
    public void testCase1(){
        UserPurchase up = new UserPurchase();
        assertNotNull(upDao.updateStatus(1,"Completed"));
    }
}
