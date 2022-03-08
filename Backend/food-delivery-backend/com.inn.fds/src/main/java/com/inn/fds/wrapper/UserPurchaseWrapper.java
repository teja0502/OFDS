package com.inn.fds.wrapper;

import lombok.Data;

@Data
public class UserPurchaseWrapper extends RestroWrapper {

    private Integer userid;

    private String pdata;

    private Integer restroId;

    private String amount;

    private String tranxid;

    private String address;

    private String status;

    private String purchaseType;

    private String restroAddress;

    private String ucontact;

    private Integer purchaseId;

    private String email;

    private String name;

//    u.purchaseData,
//    u.status,
//    u.amount,
//    u.purchaseType,
//    u.transactionId,
//    u.address,
//    u.restro.restroName,
//    u.restro.contact,
//    u.restro.address

    public UserPurchaseWrapper() {

    }

    public UserPurchaseWrapper(String pdata, String status, String amount, String purchaseType,
                               String tranxid, String address, String restroName,
                               String contact, String restroAddress, String ucontact) {
        super();
        this.pdata = pdata;
        this.status = status;
        this.amount = amount;
        this.purchaseType = purchaseType;
        this.tranxid = tranxid;
        this.address = address;
        super.setRname(restroName);
        super.setContact(contact);
        this.restroAddress = restroAddress;
        this.ucontact = ucontact;
    }

//    u.id,
//    u.purchaseData,
//    u.status,
//    u.amount,
//    u.purchaseType,
//    u.transactionId,
//    u.address,
//    u.appUser.email,
//    ud.name

    public UserPurchaseWrapper(Integer id, String pdata, String status,
                               String amount, String purchaseType,
                               String tranxid, String address,
                               String email, String name, String ucontact) {
        this.purchaseId = id;
        this.pdata = pdata;
        this.status = status;
        this.amount = amount;
        this.purchaseType = purchaseType;
        this.tranxid = tranxid;
        this.address = address;
        this.email = email;
        this.name = name;
        this.ucontact = ucontact;
    }

}
