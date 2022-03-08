package com.inn.fds.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/userpurchase")
public interface UserPurchaseRest {

    @PostMapping(path = "/purchase")
    ResponseEntity<String> purchase(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "getrecord")
    ResponseEntity<?> getRecord(@RequestParam(required = false) Integer userId,
                                @RequestParam(required = false) String status);

    @PostMapping(path = "/updatestatus")
    ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap);
}
