package com.sandeep.journalApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {

    @GetMapping("/health-check")
    public String healthCheck(){
        return "App is working fine";
    }
}
