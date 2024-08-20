package com.quiz_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";  // Provjerite da imate login.html u vašem resources/templates direktoriju
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";  // Provjerite da imate home.html u vašem resources/templates direktoriju
    }
}
