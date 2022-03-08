package com.inn.fds.wrapper;

import lombok.Data;

@Data
public class AppUserWrapper {

//    ud.appUser.email,ud.appUser.id,ud.name,ud.appUser.role

    String email;
    Integer id;
    String rname;
    String role;

    public AppUserWrapper() {

    }

    public AppUserWrapper(String email, Integer id, String rname, String role) {
        this.email = email;
        this.id = id;
        this.rname = rname;
        this.role = role;

    }

}
