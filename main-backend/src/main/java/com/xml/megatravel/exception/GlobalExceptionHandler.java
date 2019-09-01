package com.xml.megatravel.exception;

import com.xml.megatravel.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {ForbiddenOperationException.class})
    public ResponseEntity<Object> handleForbiddenOperationException(ForbiddenOperationException ex) {
        logger.error("ForbiddenOperationException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.NOT_ACCEPTABLE.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(value = {DeletionException.class})
    public ResponseEntity<Object> handleDeletionException(DeletionException ex) {
        logger.error("DeletionException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.error("EntityNotFoundException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public ResponseEntity<Object> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex) {
        logger.error("EntityAlreadyExistsException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.CONFLICT.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(value = {UserAlreadyExistsException.class})
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        logger.error("UserAlreadyExistsException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("UserNotFoundException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }


    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleUserNotFoundException(AccessDeniedException ex) {
        logger.error("AccessDeniedException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        logger.error("CustomException handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler(value = {
            S3BucketException.class,
            Exception.class
    })
    public ResponseEntity<?> handleException(Exception ex) {
        logger.error("Exception handled. Message - {}", ex.getLocalizedMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(ex.getMessage())
                        .dateTime(LocalDateTime.now())
                        .build()
                );
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        final ApiResponse response = ApiResponse.builder()
                .statusCode(status.value())
                .dateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .data(ex.getBindingResult().getAllErrors().stream().map(e -> ((FieldError) e).getField() + " " + e.getDefaultMessage()).collect(Collectors.toList()))
                .build();
        return handleException(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return handleException(ApiResponse.builder().statusCode(status.value()).dateTime(LocalDateTime.now()).message(ex.getLocalizedMessage()).build(), status);
    }

    private ResponseEntity<Object> handleException(ApiResponse body, HttpStatus status) {
        logger.info("Handling error - {}", body.getMessage());
        return ResponseEntity.status(status).body(body);
    }
}
