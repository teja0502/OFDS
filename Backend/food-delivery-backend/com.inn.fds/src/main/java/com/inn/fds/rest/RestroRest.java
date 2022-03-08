package com.inn.fds.rest;

import com.inn.fds.POJO.Restro;
import com.inn.fds.wrapper.RestroWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/restro")
public interface RestroRest {

    @PostMapping(path = "/addrestro")
    ResponseEntity<String> addRestro(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/getrestro")
    ResponseEntity<List<RestroWrapper>> getRestro();

    @PostMapping(path = "/updaterestro")
    ResponseEntity<String> updateRestro(@RequestBody(required = true) Map<String, String> requestMap);

}
