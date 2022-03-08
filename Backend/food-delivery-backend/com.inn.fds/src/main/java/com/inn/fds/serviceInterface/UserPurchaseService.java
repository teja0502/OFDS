package com.inn.fds.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserPurchaseService {

    ResponseEntity<String> purchase(Map<String, String> requestMap);

    ResponseEntity<?> getRecord(Integer userId, String status);

    ResponseEntity<String> updateStatus(Map<String, String> requestMap);

}
