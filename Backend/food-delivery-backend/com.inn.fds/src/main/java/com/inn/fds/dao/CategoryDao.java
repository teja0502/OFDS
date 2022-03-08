package com.inn.fds.dao;

import com.inn.fds.POJO.Category;
import com.inn.fds.wrapper.CategoryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    Integer getCountByRestroAndCategory(@Param("restroId") Integer restroId, @Param("cname") String cname);

    List<CategoryWrapper> getCategoryByRestro(@Param("restroId") Integer restroId);

    @Modifying
    @Transactional
    Integer updateCategory(@Param("categoryId") Integer categoryId, @Param("cname") String cname);

}
