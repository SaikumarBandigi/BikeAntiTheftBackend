package com.sb.BikeAntiTheftBackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBikeTheftAlert(String[] recipients, String deviceId, double acceleration) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipients);
        message.setSubject("Bike Anti-Theft Alert!");
        message.setText("Alert! Suspicious motion detected for Bike Device ID: " + deviceId +
                "\nAcceleration magnitude: " + acceleration + " m/s²" +
                "\nTimestamp: " + java.time.LocalDateTime.now() +
                "\n\nPlease check your bike immediately.");
        mailSender.send(message);
    }

}
