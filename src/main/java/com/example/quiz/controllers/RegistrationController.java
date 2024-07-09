package com.example.quiz.controllers;

import com.example.quiz.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Возвращает имя HTML-шаблона (например, register.html)
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username, @RequestParam String password) {
        userService.registerNewUser(username, password);
        return "redirect:/login"; // После успешной регистрации перенаправляет на страницу логина
    }
}
