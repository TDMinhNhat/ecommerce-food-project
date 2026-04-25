package io.github.tdminhnhat.core.util;

import io.minio.*;
import io.minio.errors.MinioException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileActionUtil {

    @NonFinal
    @Value("${minio.bucket-name}")
    String bucketName;

    MinioClient minioClient;

    public FileActionUtil(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostConstruct
    public void serviceInitialize() throws MinioException {
        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    public String upload(MultipartFile file, String objectName) throws MinioException, IOException {
        minioClient.putObject(PutObjectArgs.builder().stream(file.getInputStream(), file.getSize(), -1L).build());
        return getUrlFile(objectName);
    }

    public void delete(String objectName) throws MinioException {
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    public String getUrlFile(String objectName) throws MinioException {
        return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(objectName).build());
    }
}
