package com.product_service.service.impl;

import com.product_service.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class S3ServiceImpl implements S3Service {

    private final S3Client s3Client;

    public S3ServiceImpl(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    @Value("${aws.region}")
    private String region;

    private static final Set<String> ALLOWED_TYPES = Set.of("image/jpeg", "image/png", "image/webp", "application/pdf"
    );

    @Override
    public List<String> uploadFiles(MultipartFile[] files) throws IOException {
        return List.of(files)
                .stream().map(file -> {

                    try {
                        validateFile(file);
                        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
                        var request = PutObjectRequest.builder()
                                        .bucket(bucketName)
                                        .key(fileName)
                                        .contentType(file.getContentType())
                                        .build();

                        s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

                        return getFileUrl(fileName);

                    } catch (IOException e) {

                        throw new RuntimeException("Failed to upload file: " + file.getOriginalFilename(), e);
                    }
                })
                .toList();
    }

    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File cannot be empty");
        }

        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new RuntimeException("Only JPG, PNG, WEBP, PDF allowed");
        }

        long maxSize = 25 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new RuntimeException("Max file size is 50MB");
        }
    }

    private String getFileUrl(String fileName) {
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
    }
}