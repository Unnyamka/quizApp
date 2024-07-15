package com.example.quiz.controllers.user;

import com.example.quiz.entity.User;
import com.example.quiz.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @PostMapping("/perform_login")
    public String performLogin(@RequestParam String username, @RequestParam String password,
                               HttpServletRequest request, HttpServletResponse response) {
        logger.info("Attempting to log in with username: {}", username);

        try {
            request.login(username, password); // Используем встроенный метод для аутентификации
            return "redirect:/users"; // Перенаправление после успешного входа
        } catch (Exception e) {
            logger.error("Login failed", e);
            return "redirect:/login?error=true"; // Перенаправление на страницу логина с ошибкой
        }
    }
}