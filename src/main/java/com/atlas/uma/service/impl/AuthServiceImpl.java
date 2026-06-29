package com.atlas.uma.service.impl;

import com.atlas.uma.dto.request.LoginRequestDTO;
import com.atlas.uma.dto.response.CommonResponseDTO;
import com.atlas.uma.dto.response.LoginResponseDTO;
import com.atlas.uma.repository.AdminRepository;
import com.atlas.uma.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public CommonResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
