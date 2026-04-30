package io.github.tdminhnhat.core.util;

import io.minio.*;
import io.minio.errors.MinioException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    public void upload(MultipartFile file, String objectName) throws MinioException, IOException {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(this.bucketName).object(objectName)
                .stream(file.getInputStream(), (long) file.getInputStream().available(), -1L)
                .contentType(file.getContentType())
                .build();
        minioClient.putObject(putObjectArgs);
    }

    public void delete(String objectName) throws MinioException {
        RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(this.bucketName)
                .object(objectName)
                .build();
        minioClient.removeObject(removeObjectArgs);
    }

    public String getUrlFile(String objectName) throws MinioException {
        GetPresignedObjectUrlArgs getPresignedObjectUrlArgs = GetPresignedObjectUrlArgs.builder()
                .bucket(this.bucketName)
                .object(objectName)
                .expiry(24, TimeUnit.HOURS)
                .method(Http.Method.GET)
                .build();
        return minioClient.getPresignedObjectUrl(getPresignedObjectUrlArgs);
    }
}
