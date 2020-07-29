package com.crud.login.models.responses;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ErrorResponse {

    private final String description;

    public ErrorResponse(String description) {
        this.description = description;
    }

    public static ErrorResponse fromRunTimeException(RuntimeException exception) {

        return ErrorResponse.builder().description(exception.getMessage()).build();
    }

    public static ErrorResponse fromException(InvalidFormatException ex) {

        String message = "Invalid format in the fields: ";

        for (JsonMappingException.Reference r : ex.getPath()) {
            message += r.getFieldName() + " ";
        }

        return ErrorResponse.builder().description(message).build();
    }
}