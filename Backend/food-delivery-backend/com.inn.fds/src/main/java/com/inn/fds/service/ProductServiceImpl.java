package com.inn.fds.service;

import com.inn.fds.POJO.Category;
import com.inn.fds.POJO.Product;
import com.inn.fds.dao.ProductDao;
import com.inn.fds.serviceInterface.ProductService;
import com.inn.fds.utils.FoodDeliveryUtils;
import com.inn.fds.wrapper.ProductWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public ResponseEntity<String> addProduct(MultipartFile multipartFile, Map<String, String> requestMap) {
        try {
            if (validateProductMap(requestMap, false)) {
                if (productDao.getProductCountByName(requestMap.get("name").trim()) == 0) {
                    if (multipartFile.getContentType().equals("image/jpeg")) {
                        Product product = getProductFromMap(requestMap, false);
                        String fileName = FoodDeliveryUtils.getUUID();
                        product.setProductId(fileName);
                        product.setFilePath(requestMap.get("email") + File.separator + fileName + ".jpeg");
                        productDao.save(product);
                        uploadImage(requestMap.get("email"), fileName, multipartFile);
                        return new ResponseEntity<>("{\"message\":\"Product Added Successfully\"}", HttpStatus.OK);
                    } else {
                        return new ResponseEntity<>("{\"message\":\"Invalid Image type. Try with jpeg image.\"}", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return new ResponseEntity<>("{\"message\":\"Product Already Exist.\"}", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
        } catch (
                Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct(Integer restroId, boolean status) {
        log.info("Inside getAllProduct");
        try {
            if (status)
                return new ResponseEntity<List<ProductWrapper>>(productDao.getAllProduct(restroId, Arrays.asList("true")), HttpStatus.OK);
            else
                return new ResponseEntity<List<ProductWrapper>>(productDao.getAllProduct(restroId, Arrays.asList("true", "false")), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity(new ArrayList<ProductWrapper>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        System.out.println(requestMap);
        try {
            if (validateProductMap(requestMap, true)) {
                Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
                if (!optional.isEmpty()) {
                    Product product = getProductFromMap(requestMap, true);
                    product.setStatus(optional.get().getStatus());
                    productDao.save(product);
                    return new ResponseEntity<>("{\"message\":\"Product Updated Successfully\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"Product id does not exist.\"}", HttpStatus.OK);
                }
            }
            return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {

            Optional optional = productDao.findById(id);
            if (!optional.isEmpty()) {
                productDao.deleteById(id);
                return new ResponseEntity<>("{\"message\":\"Product Deleted Successfully.\"}", HttpStatus.OK);
            }
            return new ResponseEntity<>("{\"message\":\"Product id does not exist.\"}", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            Optional optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
            if (!optional.isEmpty()) {
                productDao.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                return new ResponseEntity<>("{\"message\":\"Product Status Updated Successfully.\"}", HttpStatus.OK);
            }
            return new ResponseEntity<>("{\"message\":\"Product id does not exist.\"}", HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getProductByCategoryId(Integer id) {
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<ProductWrapper>>(productDao.getProductsByCategory(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductWrapper> getProductById(Integer id) {
        return new ResponseEntity<ProductWrapper>(productDao.getProductById(id), HttpStatus.OK);
    }

    private boolean validateProductMap(Map<String, String> requestMap, Boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));
        Product product = new Product();
        if (isAdd) {
            product.setId(Integer.parseInt(requestMap.get("id")));
            product.setProductId(requestMap.get("productId"));
            product.setFilePath(requestMap.get("filePath"));
        } else {
            product.setStatus("true");
        }
        product.setName(requestMap.get("name").trim());
        product.setCategory(category);
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }

    @Override
    public ResponseEntity<String> uploadImage(String email, String fileName, MultipartFile multipartFile) {
        try {
            log.info("email : {} size : {}", email, multipartFile.getSize());
            if (multipartFile.getContentType().equals("image/jpeg")) {
                boolean uploadStatus = FoodDeliveryUtils.uploadFile(email, fileName, multipartFile);
                if (uploadStatus) {
                    return new ResponseEntity<>("{\"message\":\"Image Uploaded Successfully." + "\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"" + "Upload Failed" + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"" + "Invalid File Format" + "\"}", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"" + "Something Went Wrong." + "\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @Override
    public ResponseEntity<byte[]> getImage(String filePath) {
        log.info("inside getImage | filePath : {}", filePath);
        try {
            File initialFile = new File(FoodDeliveryUtils.STORE_LOCATION + filePath);
            System.out.println(FoodDeliveryUtils.STORE_LOCATION + filePath);
            InputStream targetStream = new FileInputStream(initialFile);
            byte[] byteArray = IOUtils.toByteArray(targetStream);
            targetStream.close();
            return new ResponseEntity<byte[]>(byteArray, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
