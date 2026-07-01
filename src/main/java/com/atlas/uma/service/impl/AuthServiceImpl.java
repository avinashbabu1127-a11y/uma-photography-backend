package com.atlas.uma.service.impl;

import com.atlas.uma.dto.request.LoginRequestDTO;
import com.atlas.uma.dto.request.VerifyOtpRequestDTO;
import com.atlas.uma.dto.response.CommonResponseDTO;
import com.atlas.uma.dto.response.LoginResponseDTO;
import com.atlas.uma.entity.Admin;
import com.atlas.uma.entity.Otp;
import com.atlas.uma.repository.AdminRepository;
import com.atlas.uma.repository.OtpRepository;
import com.atlas.uma.security.JwtService;
import com.atlas.uma.service.AuthService;
import com.atlas.uma.service.EmailService;
import com.atlas.uma.util.OtpUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final JwtService jwtService;


    @Override
    @Transactional
    public CommonResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        Admin admin = adminRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), admin.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid password");
        }

        otpRepository.deleteByEmail(admin.getEmail());
        String otp = OtpUtil.generateOtp();

        Otp otpEntity = Otp.builder()
                .email(admin.getEmail())
                .otp(otp)
                .verified(false)
                .expiryTime(LocalDateTime.now().plusMinutes(5))
                .build();

        otpRepository.save(otpEntity);

        emailService.sendOtp(admin.getEmail(), otp);

        return CommonResponseDTO.<LoginResponseDTO>builder()
                .success(true)
                .message("Otp Sent Successfully")
                .data(null)
                .build();
    }

    @Override
    public CommonResponseDTO<LoginResponseDTO> verifyOtp(
            VerifyOtpRequestDTO requestDTO) {

        Otp otpEntity = otpRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (otpEntity.isVerified()) {
            throw new RuntimeException("OTP already verified");
        }

        if (otpEntity.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP Expired");
        }

        if (!otpEntity.getOtp().equals(requestDTO.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        otpEntity.setVerified(true);
        otpRepository.save(otpEntity);

        String token = jwtService.generateToken(requestDTO.getEmail());

        LoginResponseDTO response = LoginResponseDTO.builder()
                .token(token)
                .message("Login Successful")
                .role("ADMIN")
                .build();

        return CommonResponseDTO.<LoginResponseDTO>builder()
                .success(true)
                .message("OTP Verified Successfully")
                .data(response)
                .build();
    }
}
