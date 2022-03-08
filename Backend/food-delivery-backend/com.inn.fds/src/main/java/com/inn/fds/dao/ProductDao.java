package com.inn.fds.dao;

import com.inn.fds.POJO.Product;
import com.inn.fds.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    List<ProductWrapper> getAllProduct(@Param("restroId") Integer restroId, @Param("status") List<String> status);

    @Modifying
    @Transactional
    Integer updateProductStatus(@Param("status") String status, @Param("id") Integer id);

    List<ProductWrapper> getProductsByCategory(@Param("id") Integer id);

    ProductWrapper getProductById(@Param("id") Integer id);

    Integer getProductCountByName(@Param("pname") String pname);
}
