package com.inn.fds.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/appuser")
public interface AppUserRest {

    @PostMapping(path = "/signup")
    public ResponseEntity<?> signup(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody(required = true) Map<String, String> requestMap);

}
