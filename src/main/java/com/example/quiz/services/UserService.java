package com.example.quiz.services;

import com.example.quiz.entity.User;
import com.example.quiz.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerNewUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Хешируем пароль перед сохранением
        userRepository.save(user);
    }
}
