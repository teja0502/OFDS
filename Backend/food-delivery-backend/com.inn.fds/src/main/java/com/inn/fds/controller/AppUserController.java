package com.inn.fds.controller;

import com.inn.fds.rest.AppUserRest;
import com.inn.fds.serviceInterface.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AppUserController implements AppUserRest {

    @Autowired
    AppUserService appUserService;

    @Override
    public ResponseEntity<?> signup(Map<String, String> requestMap) {
        try {
            return appUserService.signup(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<?> login(Map<String, String> requestMap) {
        try {
            return appUserService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
