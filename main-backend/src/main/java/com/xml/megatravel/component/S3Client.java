package com.xml.megatravel.component;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.xml.megatravel.config.CustomProperties;
import com.xml.megatravel.exception.S3BucketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

import static com.xml.megatravel.util.FileUtils.convertMultipartFileToFile;

@Component
@Slf4j
public class S3Client {

    private final AmazonS3 amazonS3;

    private final CustomProperties customProperties;

    private final String baseUrl;

    public S3Client(CustomProperties customProperties) {
        this.amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withRegion(customProperties.getAmazon().getRegion())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(customProperties.getAmazon().getAccessKey(), customProperties.getAmazon().getSecretKey())))
                .build();
        this.customProperties = customProperties;
        baseUrl = customProperties.getAmazon().getS3Endpoint() + customProperties.getAmazon().getBucket() + "/";
    }

    public String upload(MultipartFile multipartFile) {
        try {
            final String fileId = UUID.randomUUID().toString();
            final String fileUrl = baseUrl + fileId;
            final File file = convertMultipartFileToFile(multipartFile);
            log.info("Uploading file with id: {} to S3 bucket: {}.", fileId, customProperties.getAmazon().getBucket());
            amazonS3.putObject(new PutObjectRequest(customProperties.getAmazon().getBucket(), fileId, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            if (!file.delete()) {
                log.error("Temporary file can not be deleted!");
            }
            return fileUrl;
        } catch (Exception e) {
            throw new S3BucketException("Error while uploading file to S3. Message: " + e.getMessage());
        }
    }

    public void delete(String id) {
        log.info("Deleting file {} from S3 Bucket.", id);
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(customProperties.getAmazon().getBucket(), id));
        } catch (Exception e) {
            log.error("Error while deleting file from S3 Bucket. Message: {}", e.getMessage());
            throw new S3BucketException("Error while deleting file to S3. Message: " + e.getMessage());
        }
    }
}
