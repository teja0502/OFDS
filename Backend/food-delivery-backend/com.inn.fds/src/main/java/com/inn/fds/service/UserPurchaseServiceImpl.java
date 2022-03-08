package com.inn.fds.service;

import com.inn.fds.POJO.AppUser;
import com.inn.fds.POJO.Restro;
import com.inn.fds.POJO.UserPurchase;
import com.inn.fds.dao.UserPurchaseDao;
import com.inn.fds.serviceInterface.UserPurchaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

@Slf4j
@Service
public class UserPurchaseServiceImpl implements UserPurchaseService {

    @Autowired
    UserPurchaseDao userPurchaseDao;

    @Override
    public ResponseEntity<String> purchase(Map<String, String> requestMap) {
        try {
            if (validateRequestMap(requestMap)) {
                userPurchaseDao.save(getUserPurchaseFromMap(requestMap));
                return new ResponseEntity<String>("{\"message\":\"Order placed.\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("{\"message\":\"Improper Data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateRequestMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("userid") &&
                requestMap.containsKey("pdata") &&
                requestMap.containsKey("restroId") &&
                requestMap.containsKey("amount") &&
                requestMap.containsKey("ptype") &&
                requestMap.containsKey("tranxid") &&
                requestMap.containsKey("address") &&
                requestMap.containsKey("ucontact")
        ) {
            return true;
        }
        return false;
    }

    private UserPurchase getUserPurchaseFromMap(Map<String, String> requestMap) {
        UserPurchase userPurchase = new UserPurchase();
        AppUser appUser = new AppUser();
        appUser.setId(Integer.parseInt(requestMap.get("userid")));
        userPurchase.setAppUser(appUser);
        Restro restro = new Restro();
        restro.setId(Integer.parseInt(requestMap.get("restroId")));
        userPurchase.setRestro(restro);
        userPurchase.setPurchaseData(requestMap.get("pdata"));
        userPurchase.setAmount(requestMap.get("amount"));
        userPurchase.setPurchaseType(requestMap.get("ptype"));
        userPurchase.setTransactionId(requestMap.get("tranxid"));
        userPurchase.setStatus("Order Placed");
        userPurchase.setAddress(requestMap.get("address"));
        userPurchase.setUcontact(requestMap.get("ucontact"));
        return userPurchase;
    }

    @Override
    public ResponseEntity<?> getRecord(Integer userId, String status) {
        try {
            if (status != null && !status.isEmpty())
                return new ResponseEntity<>(userPurchaseDao.getPurchaseRecordByRestoId(userId, Arrays.asList(status.split(","))), HttpStatus.OK);
            else
                return new ResponseEntity<>(userPurchaseDao.getPurchaseRecordByUserId(userId), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if (validateUpdateMap(requestMap)) {
                if (requestMap.get("status").matches("Order Placed|Preparing|Completed")) {
                    userPurchaseDao.updateStatus(Integer.parseInt(requestMap.get("purchaseId")), requestMap.get("status"));
                    return new ResponseEntity<String>("{\"message\":\"Update Successfully.\"}", HttpStatus.OK);
                }
                return new ResponseEntity<String>("{\"message\":\"Invalid Status\"}", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<String>("{\"message\":\"Improper Data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateUpdateMap(Map<String, String> requestMap) {
        if (requestMap.containsKey("purchaseId") && requestMap.containsKey("status")) {
            return true;
        }
        return false;
    }

}
