package com.inn.fds.dao;

import com.inn.fds.POJO.UserPurchase;
import com.inn.fds.wrapper.UserPurchaseWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserPurchaseDao extends JpaRepository<UserPurchase, Integer> {

    List<UserPurchaseWrapper> getPurchaseRecordByUserId(@Param("userId") Integer userId);

    List<UserPurchaseWrapper> getPurchaseRecordByRestoId(@Param("restroId") Integer restroId,
                                                         @Param("status") List<String> status);

    @Modifying
    @Transactional
    Integer updateStatus(@Param("purchaseId") Integer purchaseId,
                         @Param("status") String status);

}
