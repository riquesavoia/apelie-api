package com.apelie.apelieapi.services.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.apelie.apelieapi.exception.FileSizeException;
import com.apelie.apelieapi.exception.FileTypeException;
import com.apelie.apelieapi.services.FileService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class FileServiceImpl implements FileService {

    private AmazonS3 client;

    @Value("${aws.s3.bucket-name}")
    private String BUCKET_NAME;

    public FileServiceImpl(@Value("${aws.s3.access-key}") String accessKey,
                           @Value("${aws.s3.secret-key}") String secretKey) {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        this.client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.SA_EAST_1)
                .build();
    }

    public String uploadFile(String encodedFile) throws IOException, FileSizeException, FileTypeException {
        if (StringUtils.isEmpty(encodedFile)) {
            return null;
        }

        String fileId = UUID.randomUUID().toString();
        File outputFile = base64ToFile(encodedFile, fileId);
        String imageType = outputFile.getName().split("\\.")[1];
        String filename = fileId + "." + imageType;

        client.putObject(BUCKET_NAME, filename, outputFile);
        outputFile.delete();

        return client.getUrl(BUCKET_NAME, filename).toString();
    }

    public void deleteImageByUrl(String url) {
        if (!StringUtils.isEmpty(url)) {
            Pattern pattern = Pattern.compile("(?:(?!/).)+$");
            Matcher matcher = pattern.matcher(url);

            if (matcher.find()) {
                String fileKey = matcher.group(0);
                client.deleteObject(BUCKET_NAME, fileKey);
            }
        }
    }

    private File base64ToFile(String encodedFile, String filename) throws IOException, FileSizeException, FileTypeException {
        Long fileSize = (long)encodedFile.length() * (3/4);
        if (fileSize > (5 * 1048576)) {
            throw new FileSizeException("File too large");
        }

        String[] encodedParts = encodedFile.split(",");
        Pattern pattern = Pattern.compile("(?<=/).*(?=;)");
        Matcher matcher = pattern.matcher(encodedParts[0]);
        matcher.find();

        String imageType = matcher.group(0);

        List<String> acceptedTypes = Arrays.asList("png", "jpeg");

        if (!acceptedTypes.contains(imageType)) {
            throw new FileTypeException("File type not accepted");
        }

        byte[] fileContent = Base64.getDecoder().decode(encodedParts[1]);
        File outputFile = new File(filename + "." + imageType);
        FileUtils.writeByteArrayToFile(outputFile, fileContent);

        return outputFile;
    }

}
