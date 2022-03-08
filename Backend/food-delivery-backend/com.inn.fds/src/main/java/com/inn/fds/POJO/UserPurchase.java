package com.inn.fds.POJO;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "UserPurchase.getPurchaseRecordByUserId", query = "select new com.inn.fds.wrapper.UserPurchaseWrapper(u.purchaseData,u.status,u.amount,u.purchaseType,u.transactionId,u.address,u.restro.restroName,u.restro.contact,u.restro.address,u.ucontact) from UserPurchase u where u.appUser.id=:userId order by u.id desc")

@NamedQuery(name = "UserPurchase.getPurchaseRecordByRestoId", query = "select new com.inn.fds.wrapper.UserPurchaseWrapper(u.id,u.purchaseData,u.status,u.amount,u.purchaseType,u.transactionId,u.address,u.appUser.email,ud.name,u.ucontact) from UserPurchase u inner join UserData ud on u.appUser.id=ud.appUser.id where u.restro.id=:restroId and u.status in (:status) order by u.id desc")

@NamedQuery(name = "UserPurchase.updateStatus", query = "update UserPurchase u set u.status=:status where u.id=:purchaseId")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "userpurchase")
public class UserPurchase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userpurchase_pk")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restro_fk", nullable = false)
    private Restro restro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_fk", nullable = false)
    private AppUser appUser;

    @Column(name = "pdata", columnDefinition = "json")
    private String purchaseData;

    @Column(name = "status")
    private String status;

    @Column(name = "amount")
    private String amount;

    @Column(name = "ptype")
    private String purchaseType;

    @Column(name = "tranxid")
    private String transactionId;

    @Column(name = "address")
    private String address;

    @Column(name = "ucontact")
    private String ucontact;

}
