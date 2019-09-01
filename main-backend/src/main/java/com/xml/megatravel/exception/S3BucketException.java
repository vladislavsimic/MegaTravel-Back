package com.xml.megatravel.exception;

public class S3BucketException extends RuntimeException {
    public S3BucketException(String message) {
        super(message);
    }

    public S3BucketException(String message, Throwable cause) {
        super(message, cause);
    }
}
