package com.example.quiz.services;

import com.example.quiz.entity.User;
import com.example.quiz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }
}
