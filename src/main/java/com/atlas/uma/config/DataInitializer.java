package com.atlas.uma.config;

import com.atlas.uma.entity.Admin;
import com.atlas.uma.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner init(AdminRepository adminRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(adminRepository.findByEmail("admin@uma.com").isEmpty()){
                Admin admin = Admin.builder()
                        .fullName("Administrator")
                        .email("admin@uma.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role("ADMIN")
                        .active(true)
                        .build();

                adminRepository.save(admin);

                System.out.println("Default Admin Created Successfully");

            }
        };

    }
}
