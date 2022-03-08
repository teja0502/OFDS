package com.inn.fds.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@NamedQuery(name = "UserData.getUserData", query = "select new com.inn.fds.wrapper.AppUserWrapper(ud.appUser.email,ud.appUser.id,ud.name,ud.appUser.role) from UserData ud where ud.appUser.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "userdata")
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appuser_fk", nullable = false)
    private AppUser appUser;

    @Column(name = "name")
    private String name;

    @Column(name = "ucontact")
    private String userContact;

}
