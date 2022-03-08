package com.inn.fds.serviceInterface;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CategoryService {

    ResponseEntity<?> addCategory(Map<String, String> requestMap);

    ResponseEntity<?> getCategory(Integer restroId);

    ResponseEntity<?> updateCategory(Map<String, String> requestMap);
}
