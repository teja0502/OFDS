package com.inn.fds.wrapper;

import lombok.Data;

@Data
public class CategoryWrapper {

    private Integer categoryId;

    private String cname;

    public CategoryWrapper(Integer categoryId, String cname) {
        this.categoryId = categoryId;
        this.cname = cname;
    }
}
