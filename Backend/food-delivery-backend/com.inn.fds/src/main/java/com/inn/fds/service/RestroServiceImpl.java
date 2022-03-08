package com.inn.fds.service;

import com.inn.fds.POJO.AppUser;
import com.inn.fds.POJO.Restro;
import com.inn.fds.dao.RestroDao;
import com.inn.fds.serviceInterface.AppUserService;
import com.inn.fds.serviceInterface.RestroService;
import com.inn.fds.utils.FoodDeliveryUtils;
import com.inn.fds.wrapper.RestroWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class RestroServiceImpl implements RestroService {

    @Autowired
    AppUserService appUserService;

    @Autowired
    RestroDao restroDao;

    @Override
    public ResponseEntity<String> addRestro(Map<String, String> requestMap) {
        try {
            if (validateRequestMap(requestMap)) {
                if (restroDao.getRestroCountByEmail(requestMap.get("email")) == 0) {
                    FoodDeliveryUtils.createPathIfNotExists(FoodDeliveryUtils.STORE_LOCATION + (requestMap.get("email").toLowerCase()));
                    requestMap.put("getuserdata", "true");
                    ResponseEntity<?> responseEntity = appUserService.signup(requestMap);
                    AppUser user = (AppUser) responseEntity.getBody();
                    requestMap.remove("getuserdata");
                    if (!Objects.isNull(user)) {
                        requestMap.put("userpk", user.getId().toString());
                        restroDao.save(getRestroFromMap(requestMap));
                        return new ResponseEntity<String>("{\"message\":\"Restaurant Added Successfully.\"}", HttpStatus.OK);
                    }
                } else {
                    return new ResponseEntity<String>("{\"message\":\"Restaurant already registered.\"}", HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<String>("{\"message\":\"Email already exist.\"}", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<String>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Restro getRestroFromMap(Map<String, String> requestMap) {
        log.info("Inside getRestroFromMap {}", requestMap);
        Restro restro = new Restro();
        restro.setRestroName(requestMap.get("rname"));
        restro.setContact(requestMap.get("contact"));
        restro.setAddress(requestMap.get("address"));
        restro.setGstNo(requestMap.get("gstno"));
        if (!requestMap.containsKey("noid")) {
            AppUser user = new AppUser();
            user.setId(Integer.parseInt(requestMap.get("userpk")));
            restro.setAppUser(user);
        } else {
            restro.setId(Integer.parseInt(requestMap.get("id")));
        }
        return restro;
    }

    private boolean validateRequestMap(Map<String, String> requestMap) {
        log.info("Inside validateRequestMap {}", requestMap);
        if (requestMap.containsKey("email") &&
                requestMap.containsKey("password") &&
                requestMap.containsKey("role") &&
                requestMap.containsKey("rname") &&
                requestMap.containsKey("contact") &&
                requestMap.containsKey("address") &&
                requestMap.containsKey("gstno")) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<List<RestroWrapper>> getRestro() {
        return new ResponseEntity<List<RestroWrapper>>(restroDao.getAllRestro(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateRestro(Map<String, String> requestMap) {
        try {
            if (requestMap.containsKey("rname") &&
                    requestMap.containsKey("contact") &&
                    requestMap.containsKey("address") &&
                    requestMap.containsKey("gstno") &&
                    requestMap.containsKey("id")) {
                Optional<Restro> optional = restroDao.findById(Integer.parseInt(requestMap.get("id")));
                if (optional.isPresent() && !optional.isEmpty()) {
                    requestMap.put("noid", "noid");
                    Restro restro = getRestroFromMap(requestMap);
                    restro.setAppUser(optional.get().getAppUser());
                    restroDao.save(restro);
                    return new ResponseEntity<String>("{\"message\":\"Restaurant updated Successfully.\"}", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("{\"message\":\"Restaurant not found with this id.\"}", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
