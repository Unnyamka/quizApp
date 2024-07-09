package com.example.quiz.controllers;

import com.example.quiz.entity.User;
import com.example.quiz.repositories.UserRepository;
import com.example.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "users"; // Возвращает имя HTML-шаблона (например, users.html)
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);
        if(user!=null){
            if(user.getRole().equals("ADMIN"))
                return "redirect:/admin/quizzes";
            else
                return "redirect:/user/quizzes";
        }
        return "redirect:/quizzes"; // Перенаправляем пользователя на защищенную страницу
    }
}