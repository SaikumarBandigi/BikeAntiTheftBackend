package com.sb.BikeAntiTheftBackend.controller;

import com.sb.BikeAntiTheftBackend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anti-theft")
public class AntiTheftController {

    private boolean isArmed = false;

    @Autowired
    private EmailService emailService;

    @GetMapping("/getStatus")
    public ResponseEntity<Boolean> getStatus() {
        return ResponseEntity.ok(isArmed);
    }

    @PostMapping("/setStatus")
    public ResponseEntity<String> setStatus(@RequestParam boolean armed) {
        this.isArmed = armed;
        return ResponseEntity.ok("System armed status set to: " + armed);
    }

    // Define a DTO class for alert data
    public static class AlertRequest {
        public String deviceId;
        public double acceleration;
    }

    @PostMapping("/sendAlert")
    public ResponseEntity<String> sendAlert(@RequestBody AlertRequest alertRequest) {
        // You can define your recipient list here
        String[] recipients = {"bandigisaikumar@gmail.com"};
        emailService.sendBikeTheftAlert(recipients, alertRequest.deviceId, alertRequest.acceleration);
        return ResponseEntity.ok("Alert email sent.");
    }

}
