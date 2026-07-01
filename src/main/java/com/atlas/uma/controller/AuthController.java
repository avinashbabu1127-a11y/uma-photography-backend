package com.atlas.uma.controller;

import com.atlas.uma.dto.request.LoginRequestDTO;
import com.atlas.uma.dto.request.VerifyOtpRequestDTO;
import com.atlas.uma.dto.response.CommonResponseDTO;
import com.atlas.uma.dto.response.LoginResponseDTO;
import com.atlas.uma.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDTO<LoginResponseDTO>> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<CommonResponseDTO<LoginResponseDTO>> verifyOtp(@Valid @RequestBody VerifyOtpRequestDTO verifyOtpRequestDTO){
        return ResponseEntity.ok(authService.verifyOtp(verifyOtpRequestDTO));
    }
}
