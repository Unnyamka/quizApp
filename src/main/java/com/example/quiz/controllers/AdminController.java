package com.example.quiz.controllers;

import com.example.quiz.entity.Answer;
import com.example.quiz.entity.Question;
import com.example.quiz.entity.Quiz;
import com.example.quiz.services.AnswerService;
import com.example.quiz.services.QuestionService;
import com.example.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private QuizService quizService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private QuestionService questionService;

    @GetMapping("/quizzes")
    public String getQuizzes(Model model){
        model.addAttribute("quizzes", quizService.findAll());
        return "admin/quizzes";
    }

    @PostMapping("/quizzes")
    public String createQuiz(@ModelAttribute Quiz quiz){
        quizService.save(quiz);
        return "redirect:/admin/quizzes";
    }

    @GetMapping("/questions")
    public String getQuestions(Model model){
        model.addAttribute("questions", questionService.findAlL());
        return "admin/questions";
    }

    @PostMapping("/questions")
    public String createQuestion(@ModelAttribute Question question){
        questionService.save(question);
        return "redirect:/admin/questions";
    }

    @GetMapping("/answers")
    public String getAnswers(Model model){
        model.addAttribute("answers", answerService.findAll());
        return "admin/answers";
    }

    @PostMapping("/answers")
    public String createAnswer(@ModelAttribute Answer answer){
        answerService.save(answer);
        return "redirect:/admin/answers";
    }
}
