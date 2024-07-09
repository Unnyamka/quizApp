package com.example.quiz.controllers;

import com.example.quiz.entity.Quiz;
import com.example.quiz.entity.User;
import com.example.quiz.repositories.UserRepository;
import com.example.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private QuizService quizService;

    @GetMapping("/quizzes")
    public String getQuizzes(Model model){
        model.addAttribute("quizzes", quizService.findAll());
        return "user/quizzes";
    }

    @GetMapping("/quizzes/{id}")
    public String getQuiz(@PathVariable Long id, Model model){
        Quiz quiz = quizService.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid quiz Id: "+id));
        model.addAttribute("quiz", quiz);
        return "user/quiz";
    }

    @PostMapping("/quizzes/{id}")
    public String submitQuiz(@PathVariable Long id, @RequestParam Map<String, String> answers){
        //Обработка ответов и оценка результатов
        return "redirect:/user/quizzes";
    }



}
