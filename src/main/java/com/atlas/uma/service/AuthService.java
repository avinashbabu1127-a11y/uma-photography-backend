package com.atlas.uma.service;

import com.atlas.uma.dto.request.LoginRequestDTO;
import com.atlas.uma.dto.request.VerifyOtpRequestDTO;
import com.atlas.uma.dto.response.CommonResponseDTO;
import com.atlas.uma.dto.response.LoginResponseDTO;

public interface AuthService {
    CommonResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO);

    CommonResponseDTO<LoginResponseDTO> verifyOtp(VerifyOtpRequestDTO verifyOtpRequestDTO);
}
