package com.inn.fds.wrapper;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ProductWrapper {

    Integer id;

    String name;

    String description;

    Integer price;

    String status;

    Integer categoryId;

    String categoryName;

    String productId;

    String filePath;

    public ProductWrapper() {

    }

    public ProductWrapper(Integer id, String name, String description, Integer price, String status,
                          Integer categoryId, String categoryName, String productId, String filePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.productId = productId;
        this.filePath = filePath;
    }

    public ProductWrapper(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductWrapper(Integer id, String name, String description, Integer price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
