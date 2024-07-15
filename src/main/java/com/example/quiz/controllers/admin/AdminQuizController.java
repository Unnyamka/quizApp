package com.example.quiz.controllers.admin;

import com.example.quiz.entity.Quiz;
import com.example.quiz.services.QuizService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminQuizController {

    private final QuizService quizService;

    public AdminQuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // Страница добавления новой викторины
    @GetMapping("/addQuiz")
    public String addQuizForm(Model model) {
        model.addAttribute("quiz", new Quiz());
        return "admin/add-quiz";
    }

    // Обработка POST-запроса на добавление новой викторины
    @PostMapping("/addQuiz")
    public String addQuizSubmit(@ModelAttribute("quiz") Quiz quiz) {
        quizService.addQuiz(quiz); // Предположим, что у вас есть метод в сервисе для добавления викторины
        return "redirect:/quizzes";
    }

    // Страница редактирования существующей викторины
    @GetMapping("/editQuiz/{id}")
    public String editQuizForm(@PathVariable Long id, Model model) {
        Quiz quiz = quizService.findById(id); // Предположим, что у вас есть метод для получения викторины по id
        model.addAttribute("quiz", quiz);
        return "admin/edit-quiz";
    }

    // Обработка POST-запроса на редактирование существующей викторины
    @PostMapping("/editQuiz/{id}")
    public String editQuizSubmit(@PathVariable Long id, @ModelAttribute("quiz") Quiz quiz) {
        quiz.setId(id); // Установка ID, чтобы обновить существующую викторину
        quizService.updateQuiz(quiz); // Предположим, что у вас есть метод в сервисе для обновления викторины
        return "redirect:/admin/quizzes";
    }
}
