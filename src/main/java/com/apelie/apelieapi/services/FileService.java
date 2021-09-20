package com.apelie.apelieapi.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    /**
     * Uploads a file based on its base64 content
     *
     * @param base64
     * @return
     */
    String uploadFile(String base64);

    /**
     * Uploads a file based on its MultipartFile content
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    /**
     * Deletes an image given its url
     *
     * @param url
     */
    void deleteImageByUrl(String url);
}
