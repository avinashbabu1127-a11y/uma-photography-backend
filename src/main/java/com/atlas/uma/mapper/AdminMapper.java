package com.atlas.uma.mapper;

import com.atlas.uma.dto.request.LoginRequestDTO;
import com.atlas.uma.dto.response.LoginResponseDTO;
import com.atlas.uma.entity.Admin;

public class AdminMapper {

    private AdminMapper(){

    }

    public static Admin toEntity(LoginRequestDTO dto){
            Admin admin = new Admin();


            admin.setEmail(dto.getEmail());
            admin.setPassword(dto.getPassword());

            return admin;
    }


    public static LoginResponseDTO toDto(Admin admin){
            LoginResponseDTO dto = new LoginResponseDTO();

            dto.setId(admin.getId());
            dto.setFullName(admin.getFullName());
            dto.setEmail(admin.getEmail());
            dto.setRole(admin.getRole());

            return dto;
    }
}
