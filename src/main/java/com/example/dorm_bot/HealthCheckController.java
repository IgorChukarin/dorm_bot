package com.example.dorm_bot;

import org.springframework.web.bind.annotation.GetMapping;

public class HealthCheckController {
    @GetMapping("/")
    public String healthCheck() {
        return "Bot is running!";
    }
}
