package com.atlas.uma.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "Email is Required")
    @Email(message = "Email is Invalid")
    private String email;

    @NotBlank(message = "Password is Required")
    private String password;
}
