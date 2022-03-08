package com.inn.fds.service;

import com.inn.fds.POJO.AppUser;
import com.inn.fds.POJO.UserData;
import com.inn.fds.dao.AppUserDao;
import com.inn.fds.dao.UserDataDao;
import com.inn.fds.serviceInterface.AppUserService;
import com.inn.fds.wrapper.RestroWrapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Data
@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    AppUserDao appUserDao;

    @Autowired
    UserDataDao userDataDao;

    @Override
    public ResponseEntity<?> signup(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            if (validateSignupMap(requestMap)) {
                AppUser userRes = appUserDao.findByEmailId(requestMap.get("email").toLowerCase());
                if (Objects.isNull(userRes)) {
                    AppUser user = appUserDao.save(getUserFromMap(requestMap));
                    if (requestMap.get("role").equalsIgnoreCase("user")) {
                        if (requestMap.containsKey("name") && requestMap.containsKey("ucontact")) {
                            UserData userData = new UserData();
                            userData.setAppUser(user);
                            userData.setName(requestMap.get("name"));
                            userData.setUserContact(requestMap.get("ucontact"));
                            userDataDao.save(userData);
                        } else {
                            appUserDao.deleteById(user.getId());
                            return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
                        }
                    }
                    if (requestMap.containsKey("getuserdata") && requestMap.get("getuserdata") == "true") {
                        return new ResponseEntity<>(user, HttpStatus.OK);
                    }
                    return new ResponseEntity<>("{\"message\":\"Successfully Registered.\"}", HttpStatus.OK);
                } else {
                    if (requestMap.containsKey("getuserdata") && requestMap.get("getuserdata") == "true") {
                        return new ResponseEntity<>(null, HttpStatus.OK);
                    }
                    return new ResponseEntity<>("{\"message\":\"Email already exist.\"}", HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private AppUser getUserFromMap(Map<String, String> requestMap) {
        log.info("Inside getUserFromMap {}", requestMap);
        AppUser user = new AppUser();
        user.setEmail(requestMap.get("email").toLowerCase());
        user.setPassword(requestMap.get("password"));
        user.setRole(requestMap.get("role"));
        return user;
    }

    private boolean validateSignupMap(Map<String, String> requestMap) {
        log.info("Inside validateSignupMap {}", requestMap);
        if (requestMap.containsKey("email") &&
                requestMap.containsKey("password") &&
                requestMap.containsKey("role")) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseEntity<?> login(Map<String, String> requestMap) {
        try {
            if (!requestMap.containsKey("email") || !requestMap.containsKey("pass"))
                return new ResponseEntity<>("{\"message\":\"Mandatory data not provided. \"}", HttpStatus.BAD_REQUEST);

            AppUser user = appUserDao.getUserByUsernameAndPassword(requestMap.get("email"), requestMap.get("pass"));
            if (!Objects.isNull(user) && user.getRole().equalsIgnoreCase("restro")) {
                RestroWrapper wrapper = appUserDao.findUserByUsernameAndPassword(requestMap.get("email"), requestMap.get("pass"));
                log.info("wrapper -----------> {}", wrapper);
                if (!Objects.isNull(wrapper)) {
                    return new ResponseEntity<>(wrapper, HttpStatus.OK);
                }
            } else if (!Objects.isNull(user) && user.getRole().equalsIgnoreCase("user")) {
                return new ResponseEntity<>(userDataDao.getUserData(user.getId()), HttpStatus.OK);
            } else if (!Objects.isNull(user)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return new ResponseEntity<>("{\"message\":\"Bad Credentials.\"}", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
