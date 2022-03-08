package com.inn.fds.controller;

import com.inn.fds.rest.RestroRest;
import com.inn.fds.serviceInterface.RestroService;
import com.inn.fds.wrapper.RestroWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RestroController implements RestroRest {

    @Autowired
    RestroService restroService;

    @Override
    public ResponseEntity<String> addRestro(Map<String, String> requestMap) {
        try {
            return restroService.addRestro(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<RestroWrapper>> getRestro() {
        try {
            return restroService.getRestro();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateRestro(Map<String, String> requestMap) {
        try {
            return restroService.updateRestro(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
