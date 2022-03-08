package com.inn.fds.serviceInterface;


import com.inn.fds.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {

    public ResponseEntity<String> addProduct(MultipartFile multipartFile,Map<String, String> requestMap);

    public ResponseEntity<List<ProductWrapper>> getAllProduct(Integer restroId, boolean status);

    public ResponseEntity<String> updateProduct(Map<String, String> requestMap);

    public ResponseEntity<String> deleteProduct(Integer id);

    public ResponseEntity<String> updateStatus(Map<String, String> requestMap);

    public ResponseEntity<List<ProductWrapper>> getProductByCategoryId(Integer id);

    public ResponseEntity<ProductWrapper> getProductById(Integer id);

    ResponseEntity<String> uploadImage(String email,String fileName, MultipartFile multipartFile);

    ResponseEntity<byte[]> getImage(String filePath);


}
