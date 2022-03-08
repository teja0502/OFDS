package com.inn.fds.dao;

import com.inn.fds.POJO.AppUser;
import com.inn.fds.wrapper.AppUserWrapper;
import com.inn.fds.wrapper.RestroWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface AppUserDao extends JpaRepository<AppUser, Integer> {

    AppUser findByEmailId(@Param("email") String email);

    RestroWrapper findUserByUsernameAndPassword(@Param("email") String email, @Param("pass") String pass);

    AppUser getUserByUsernameAndPassword(@Param("email") String email, @Param("pass") String pass);

}
