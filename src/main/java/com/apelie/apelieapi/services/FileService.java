package com.apelie.apelieapi.services;

import com.apelie.apelieapi.exception.FileSizeException;
import com.apelie.apelieapi.exception.FileTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    /**
     * Uploads a file based on its base64 content
     *
     * @param encodedFile
     * @return
     */
    String uploadFile(String encodedFile) throws IOException, FileSizeException, FileTypeException;

    /**
     * Deletes an image given its url
     *
     * @param url
     */
    void deleteImageByUrl(String url);
}
