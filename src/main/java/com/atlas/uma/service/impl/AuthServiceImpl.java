package com.atlas.uma.service.impl;

import com.atlas.uma.dto.request.LoginRequestDTO;
import com.atlas.uma.dto.response.CommonResponseDTO;
import com.atlas.uma.dto.response.LoginResponseDTO;
import com.atlas.uma.entity.Admin;
import com.atlas.uma.entity.Otp;
import com.atlas.uma.repository.AdminRepository;
import com.atlas.uma.repository.OtpRepository;
import com.atlas.uma.service.AuthService;
import com.atlas.uma.service.EmailService;
import com.atlas.uma.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;
    private final EmailService emailService;


    @Override
    public CommonResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        Admin admin = adminRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("invalid Email"));

        if(!passwordEncoder.matches(loginRequestDTO.getPassword(),admin.getPassword())){
                throw new RuntimeException("invalid password");
        }

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
}
