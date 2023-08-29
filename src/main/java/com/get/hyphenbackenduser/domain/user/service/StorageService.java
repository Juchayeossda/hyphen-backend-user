package com.get.hyphenbackenduser.domain.user.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {

    List<String> saveFiles(MultipartFile[] files, String postName) throws IOException;
    List<String> saveFile(MultipartFile file, String userName, String originFileName) throws IOException;
    Resource loadFileAsResource(String userName, String fileName);
}
