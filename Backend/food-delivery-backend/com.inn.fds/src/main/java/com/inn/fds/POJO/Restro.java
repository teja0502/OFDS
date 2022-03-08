package com.inn.fds.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Restro.getAllRestro", query = "Select new  com.inn.fds.wrapper.RestroWrapper(r.id,r.restroName,r.appUser.email,r.contact,r.address,r.gstNo) from Restro r")

@NamedQuery(name = "Restro.getRestroCountByEmail", query = "select count(r) from Restro r where r.appUser.email=:email")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "restro")
public class Restro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restro_pk")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appuser_fk", nullable = false)
    private AppUser appUser;

    @Column(name = "name")
    private String restroName;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "gstno")
    private String gstNo;

}
