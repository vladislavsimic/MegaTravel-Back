package com.xml.megatravel.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xml.megatravel.util.DateTimeUtil;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ApiResponse {

    private int statusCode;

    private String message;

    private List<String> data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_FORMAT)
    private LocalDateTime dateTime;
}
