package com.inn.fds.rest;

import com.inn.fds.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/product")
@CrossOrigin(origins = "*")
public interface ProductRest {

    @PostMapping(path = "/addproduct")
    public ResponseEntity<String> addProduct(@RequestParam String data, @RequestParam("multipartFile") MultipartFile multipartFile
    );
//                                             @RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/getallproduct/{restroId}")
    public ResponseEntity<List<ProductWrapper>> getAllProduct(
            @PathVariable Integer restroId,
            @RequestParam(required = false) boolean status);

    @PostMapping(path = "/updateproduct")
    public ResponseEntity<String> updateProduct(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/deleteproduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id);

    @PostMapping(path = "/updatestatus")
    public ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/getproductbycategoryid/{id}")
    public ResponseEntity<List<ProductWrapper>> getProductByCategoryId(@PathVariable Integer id);

    @GetMapping(path = "/getproductbyid/{id}")
    public ResponseEntity<ProductWrapper> getProductById(@PathVariable Integer id);

    @PostMapping(path = "/uploadimage")
    ResponseEntity<String> uploadImage(@RequestParam("email") String email, @RequestParam("fileName") String fileName,
                                       @RequestParam("multipartFile") MultipartFile multipartFile);

    @PostMapping(value = "/getImage")
    ResponseEntity<byte[]> getImage(@RequestBody Map<String, String> requestMap);


}
