package com.crud.login.models.dtos;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import static com.crud.login.utils.DtoErrorMessage.MANDATORY_NAME;
import static com.crud.login.utils.DtoErrorMessage.MANDATORY_LAST_NAME;
import static com.crud.login.utils.DtoErrorMessage.MANDATORY_EMAIL;
import static com.crud.login.utils.DtoErrorMessage.MANDATORY_PASSWORD;
import static com.crud.login.utils.DtoErrorMessage.INVALID_NAME_FORMAT;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AddUserDTO {

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = INVALID_NAME_FORMAT)
    @NotBlank(message = MANDATORY_NAME)
    private String name;

    @Pattern(regexp = "^[\\p{L} .'-]+$", message = INVALID_NAME_FORMAT)
    @NotBlank(message = MANDATORY_LAST_NAME)
    private String lastName;

    @Email
    @NotBlank(message = MANDATORY_EMAIL)
    private String email;

    @NotBlank(message = MANDATORY_PASSWORD)
    private String password;

}
