package com.inn.fds.wrapper;

import lombok.Data;

@Data
public class RestroWrapper {

    private Integer id;

    private String rname;

    private String contact;

    private String address;

    private String gstno;

    private String email;

    private String role;

    public RestroWrapper() {

    }

    public RestroWrapper(Integer id, String rname, String email, String contact, String address, String gstno) {
        this.id = id;
        this.rname = rname;
        this.contact = contact;
        this.address = address;
        this.gstno = gstno;
        this.email = email;
    }

    public RestroWrapper(Integer id, String rname, String email, String role) {
        this.id = id;
        this.email = email;
        this.rname = rname;
        this.role = role;
    }
}
