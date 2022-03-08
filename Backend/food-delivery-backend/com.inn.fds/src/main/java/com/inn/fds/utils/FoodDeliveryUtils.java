package com.inn.fds.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Slf4j
public class FoodDeliveryUtils {

    private FoodDeliveryUtils() {
    }

    public static final String STORE_LOCATION = "F:\\Images\\";

    public static void createPathIfNotExists(String path) {
        log.info("Inside createPathIfNotExists");
        try {
            File folder = new File(path);
            if (!folder.exists())
                folder.mkdir();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Boolean isFileExist(String path) {
        try {
            log.info("Inside isFileExists {}", path);
            File file = new File(path);
            return (file != null && file.exists()) ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean uploadFile(String email, String fileName, MultipartFile multipartFile) {
        try {
            String finalPath = STORE_LOCATION + email;
            createPathIfNotExists(finalPath);
            Files.copy(multipartFile.getInputStream(), Paths.get(finalPath + File.separator + fileName + ".jpeg"), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getUUID() {
        Date date = new Date();
        long time = date.getTime();
        return "PRD-" + time;
    }

}
