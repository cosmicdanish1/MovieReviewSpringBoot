package com.example.SpringBoot.controler;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChecker {


    @GetMapping("/healthChecker")
    public String healthChecker(){
        return "Ok danish is amazing";
    }
}
