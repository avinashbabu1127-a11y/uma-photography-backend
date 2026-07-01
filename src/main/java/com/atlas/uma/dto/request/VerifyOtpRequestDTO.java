package com.atlas.uma.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VerifyOtpRequestDTO {

    @NotBlank(message = "Email is required")
    @Email(message = "invalid email")
    private String email;

    @NotBlank(message = "Otp is required")
    private String otp;
}
