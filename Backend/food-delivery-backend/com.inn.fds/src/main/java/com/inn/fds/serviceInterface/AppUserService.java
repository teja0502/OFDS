package com.inn.fds.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AppUserService {

    ResponseEntity<?> signup(Map<String, String> requestMap);

    ResponseEntity<?> login(Map<String, String> requestMap);
}
