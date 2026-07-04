package com.bigboss.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {
    @GetMapping("/status")
    public Map<String,String> health(){
        return Map.of(
                "status","UP"
        );
    }
    
    @GetMapping("/public")
    public String publicApi() {
        return "This is public API. No login required.";
    }

    @GetMapping("/user")
    public String userApi() {
        return "Welcome USER. You are authenticated.";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "Welcome ADMIN. You are authorized.";
    }
}
