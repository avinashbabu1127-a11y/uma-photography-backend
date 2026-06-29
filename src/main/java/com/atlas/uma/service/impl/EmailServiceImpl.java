package com.atlas.uma.service.impl;

import com.atlas.uma.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("UMA Photography- Otp verification");
        message.setText(
                "Hello,\n\n" +
                        "Your OTP is : " + otp +
                        "\n\nThis OTP is valid for 5 minutes.\n\n" +
                        "Thank You,\nUMA Photography"
        );
        mailSender.send(message);
    }
}
