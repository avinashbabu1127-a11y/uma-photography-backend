package com.atlas.uma.service.impl;

import com.atlas.uma.dto.request.LoginRequestDTO;
import com.atlas.uma.dto.response.CommonResponseDTO;
import com.atlas.uma.dto.response.LoginResponseDTO;
import com.atlas.uma.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Override
    public CommonResponseDTO<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
