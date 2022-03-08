package com.inn.fds.dao;

import com.inn.fds.POJO.AppUser;
import com.inn.fds.POJO.Restro;
import com.inn.fds.wrapper.RestroWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestroDao extends JpaRepository<Restro, Integer> {

    List<RestroWrapper> getAllRestro();

    Integer getRestroCountByEmail(@Param("email") String email);

}
