package com.inn.fds.serviceInterface;

import com.inn.fds.wrapper.RestroWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface RestroService {

    ResponseEntity<String> addRestro(Map<String, String> requestMap);

    ResponseEntity<List<RestroWrapper>> getRestro();

    ResponseEntity<String> updateRestro(Map<String, String> requestMap);
}
