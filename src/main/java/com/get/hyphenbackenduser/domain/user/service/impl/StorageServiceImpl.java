package com.get.hyphenbackenduser.domain.user.service.impl;

import com.get.hyphenbackenduser.domain.user.exception.ImageNotFoundException;
import com.get.hyphenbackenduser.domain.user.service.StorageService;
import com.get.hyphenbackenduser.global.properties.ImageStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class StorageServiceImpl implements StorageService {

    private String imageFilePath;

    @Autowired
    public StorageServiceImpl(ImageStorageProperties imageStorageProperties){
        this.imageFilePath = imageStorageProperties.getPath();
    }

    private String getRandomStr(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public List<String> saveFiles(MultipartFile[] files, String postName) throws IOException {
        String randomStr = getRandomStr();
        List<String> fileNames = new ArrayList<>();
        for(MultipartFile file : files) {
            fileNames.add(randomStr + StringUtils.cleanPath(file.getOriginalFilename()));
        }
        Path uploadPath = Paths.get(this.imageFilePath+"/"+postName);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        for(int i=0; i<files.length; i++) {
            try (InputStream inputStream = files[i].getInputStream()) {
                Path filePath = uploadPath.resolve(fileNames.get(i));
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ioe) {
                throw new IOException("Could not save image file: " + fileNames.get(i), ioe);
            }
        }
        return fileNames;
    }

    public List<String> saveFile(MultipartFile file, String userName, String originFileName) throws IOException {
        String randomStr = getRandomStr();
        String fileName = LocalDateTime.now() + "_" + randomStr + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get(this.imageFilePath+"/"+userName);
        if(!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        } else {
            Path deletePath = Paths.get(this.imageFilePath+"/"+userName+"/"+originFileName);
            Files.delete(deletePath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            String imagePath = String.format("%s/%s/%s", this.imageFilePath, userName, fileName);
            return new ArrayList<>(List.of(imagePath, fileName));
        } catch (IOException e) {
            return null;
        }
    }

    public Resource loadFileAsResource(String userName, String fileName) {
        Path uploadPath = Paths.get(this.imageFilePath+"/"+userName);
        try {
            Path filePath = uploadPath.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw ImageNotFoundException.EXCEPTION;
            }
        } catch (MalformedURLException e) {
            throw ImageNotFoundException.EXCEPTION;
        }
    }
}
