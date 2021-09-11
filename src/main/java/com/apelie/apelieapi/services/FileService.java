package com.apelie.apelieapi.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(String base64);
    String uploadFile(MultipartFile file);
    void deleteImageByUrl(String url);
}
