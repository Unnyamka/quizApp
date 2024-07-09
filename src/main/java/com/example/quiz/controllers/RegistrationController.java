package com.example.quiz.controllers;

import com.example.quiz.entity.User;
import com.example.quiz.repositories.UserRepository;
import com.example.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {



    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password) {
        // Здесь можно сохранить нового пользователя в базу данных

        return "redirect:/login"; // После регистрации перенаправляем на страницу входа
    }
}