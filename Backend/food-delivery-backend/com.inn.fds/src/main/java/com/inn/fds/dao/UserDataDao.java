package com.inn.fds.dao;

import com.inn.fds.POJO.UserData;
import com.inn.fds.wrapper.AppUserWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserDataDao extends JpaRepository<UserData, Integer> {

    AppUserWrapper getUserData(@Param("id") Integer id);

}
