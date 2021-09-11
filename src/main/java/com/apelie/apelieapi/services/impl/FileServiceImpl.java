package com.apelie.apelieapi.services.impl;

import com.apelie.apelieapi.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    public String uploadFile(String base64) {
        return "link.com/image.png";
    }

    public String uploadFile(MultipartFile file) {
        return "link.com/image.png";
    }

    public void deleteImageByUrl(String url) { return; }
}
