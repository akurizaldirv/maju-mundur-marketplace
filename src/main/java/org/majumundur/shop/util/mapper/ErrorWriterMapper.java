package org.majumundur.shop.util.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.majumundur.shop.model.response.ErrorResponse;

import java.time.LocalDateTime;

public class ErrorWriterMapper {
    public static String mapToString(String message, Integer statusCode) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        ErrorResponse errorResponse = new ErrorResponse(statusCode, message, LocalDateTime.now().toString());
        return objectMapper.writeValueAsString(errorResponse);
    }
}

