package com.inn.fds.service;

import com.inn.fds.POJO.Category;
import com.inn.fds.POJO.Restro;
import com.inn.fds.dao.CategoryDao;
import com.inn.fds.serviceInterface.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public ResponseEntity<?> addCategory(Map<String, String> requestMap) {
        try {
            if (validateCategoryMap(requestMap)) {
                if (categoryDao.getCountByRestroAndCategory(Integer.parseInt(requestMap.get("restroId")), requestMap.get("cname").trim()) == 0) {
                    categoryDao.save(getCategoryFromMap(requestMap));
                    return new ResponseEntity<>("{\"message\":\"Category Added Successfully. \"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("{\"message\":\"Category " + requestMap.get("cname").trim() + " Already Exist.\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"Mandatory data not provided. \"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateCategoryMap(Map<String, String> requestMap) {
        if ((requestMap.containsKey("restroId") || requestMap.containsKey("categoryId")) &&
                requestMap.containsKey("cname")) {
            return true;
        }
        return false;
    }

    private Category getCategoryFromMap(Map<String, String> requestMap) {
        Category category = new Category();
        Restro restro = new Restro();
        restro.setId(Integer.parseInt(requestMap.get("restroId")));
        category.setRestro(restro);
        category.setCategoryName(requestMap.get("cname").trim());
        return category;
    }

    @Override
    public ResponseEntity<?> getCategory(Integer restroId) {
        if (restroId != null)
            return new ResponseEntity<>(categoryDao.getCategoryByRestro(restroId), HttpStatus.OK);
        return new ResponseEntity<>("{\"message\":\"Mandatory data not provided. \"}", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<?> updateCategory(Map<String, String> requestMap) {
        try {
            if (validateCategoryMap(requestMap)) {
                Integer count = categoryDao.updateCategory(Integer.parseInt(requestMap.get("categoryId")), requestMap.get("cname"));
                if (count > 0)
                    return new ResponseEntity<>("{\"message\":\"Category Updated Successfully. \"}", HttpStatus.OK);
                else
                    return new ResponseEntity<>("{\"message\":\"Unable to Update. \"}", HttpStatus.OK);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
