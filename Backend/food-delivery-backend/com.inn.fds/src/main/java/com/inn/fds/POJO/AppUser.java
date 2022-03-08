package com.inn.fds.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "AppUser.findByEmailId", query = "Select u from AppUser u where u.email=:email")

@NamedQuery(name = "AppUser.findUserByUsernameAndPassword", query = "select new  com.inn.fds.wrapper.RestroWrapper(r.id,r.restroName,u.email,u.role) from AppUser u inner join Restro r on u.id=r.appUser.id where u.email=:email and u.password=:pass")

@NamedQuery(name = "AppUser.getUserByUsernameAndPassword", query = "Select u from AppUser u where u.email=:email and u.password=:pass")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "appuser")
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appuser_pk")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}
