package com.inn.fds.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Category.getCountByRestroAndCategory", query = "Select count(c) from Category c where c.restro.id=:restroId and c.categoryName=:cname")

@NamedQuery(name = "Category.getCategoryByRestro", query = "select new com.inn.fds.wrapper.CategoryWrapper(c.id,c.categoryName) from Category c where c.restro.id=:restroId")

@NamedQuery(name = "Category.updateCategory", query = "update Category c set c.categoryName=:cname where c.id=:categoryId")


@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_pk")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restro_fk", nullable = false)
    private Restro restro;

    @Column(name = "cname")
    private String categoryName;

}
