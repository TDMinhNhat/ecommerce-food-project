package io.github.tdminhnhat.core.config;

import io.minio.MinioClient;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperties(value = {
        @ConditionalOnProperty(value = "minio.endpoint", matchIfMissing = true),
        @ConditionalOnProperty(value = "minio.access-key", matchIfMissing = true),
        @ConditionalOnProperty(value = "minio.secret-key", matchIfMissing = true),
        @ConditionalOnProperty(value = "minio.bucket-name", matchIfMissing = true),
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MinioConfig {

    @Value("${minio.endpoint}")
    String endpoint;

    @Value("${minio.access-key}")
    String accessKey;

    @Value("${minio.secret-key}")
    String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
