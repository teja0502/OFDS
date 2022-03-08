package com.inn.fds.controller;

import com.inn.fds.rest.UserPurchaseRest;
import com.inn.fds.serviceInterface.UserPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserPurchaseController implements UserPurchaseRest {

    @Autowired
    UserPurchaseService userPurchaseService;

    @Override
    public ResponseEntity<String> purchase(Map<String, String> requestMap) {
        try {
            return userPurchaseService.purchase(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> getRecord(Integer userId, String status) {
        try {
            return userPurchaseService.getRecord(userId, status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            return userPurchaseService.updateStatus(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
