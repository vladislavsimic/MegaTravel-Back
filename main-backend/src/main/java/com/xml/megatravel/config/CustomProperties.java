package com.xml.megatravel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties({CustomProperties.class})
@ConfigurationProperties(prefix = "properties", ignoreUnknownFields = false)
public class CustomProperties {
    private String apiUrl;

    private String jwtSecret;

    private int jwtExpirationInMs;

    private Long maxAgeSecs;

    private String frontBaseUrl;

    private String defaultStorageLocation;

    private final Amazon amazon = new Amazon();

    @Data
    public static class Amazon {
        private String accessKey;
        private String secretKey;
        private String region;
        private String s3Endpoint;
        private String bucket;
    }
}
