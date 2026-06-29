package com.atlas.uma.repository;

import com.atlas.uma.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp,Long>{
    Optional<Otp> findByEmail(String email);
}
