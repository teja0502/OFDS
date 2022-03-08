package com.inn.fds.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@NamedQuery(name = "Product.getAllProduct", query = "select new com.inn.fds.wrapper.ProductWrapper(p.id,p.name,p" +
        ".description,p.price,p.status,p.category.id,p.category.categoryName,p.productId,p.filePath) from Product p where p.category.restro.id=:restroId and p.status in (:status)")

@NamedQuery(name = "Product.updateProductStatus", query = "Update Product p set p.status=:status where p.id=:id")

@NamedQuery(name = "Product.getProductsByCategory", query = "select new com.inn.fds.wrapper.ProductWrapper(p.id,p" +
        ".name) from Product p where p.category.id=:id and p.status='true'")

@NamedQuery(name = "Product.getProductById", query = "select new com.inn.fds.wrapper.ProductWrapper(p.id,p" +
        ".name,p.description,p.price) from Product p where p.id=:id")

@NamedQuery(name = "Product.getProductCountByName", query = "select count(p) from Product p where p.name=:pname")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
public class Product implements Serializable {

    public static final Long serialVersionUID = 1235465l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "status")
    private String status;

    @Column(name = "productid")
    private String productId;

    @Column(name = "filepath")
    private String filePath;
}

