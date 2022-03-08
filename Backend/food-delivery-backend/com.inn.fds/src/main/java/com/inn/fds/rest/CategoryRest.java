package com.inn.fds.rest;

import com.inn.fds.wrapper.RestroWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/category")
public interface CategoryRest {

    @PostMapping(path = "/addcategory")
    ResponseEntity<?> addCategory(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/getcategory/{restroId}")
    ResponseEntity<?> getCategory(@PathVariable("restroId") Integer restroId);

    @PostMapping(path = "/updatecategory")
    ResponseEntity<?> updateCategory(@RequestBody(required = true) Map<String, String> requestMap);
}
