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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @GetMapping("/quizzes")
    public String listQuizzes(Model model) {
        model.addAttribute("quizzes", quizService.findAll());
        return "quizzes";
    }

    @GetMapping("/quiz/{id}")
    public String viewQuiz(@PathVariable("id") Long id, Model model) {
        Quiz quiz = quizService.findById(id);
        if (quiz == null) {
            // Обработка случая, когда викторина не найдена (например, перенаправление на страницу ошибки)
            return "redirect:/quizzes";
        }
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questionService.findByQuizId(id));
        return "quiz-detail";
    }

    @PostMapping("/quiz/check")
    public String checkQuizAnswers(@RequestParam Map<String, String> params, Model model) {
        Long quizId = Long.parseLong(params.get("quizId"));
        Quiz quiz = quizService.findById(quizId);
        List<Question> questions = questionService.findByQuizId(quizId);

        int correctAnswers = 0;
        int totalQuestions = questions.size();

        for (Question question : questions) {
            String[] selectedAnswers = params.getOrDefault("question_" + question.getId() + "_answer", "").split(",");
            List<Answer> correctAnswersForQuestion = answerService.findCorrectAnswersByQuestionId(question.getId());

            boolean allCorrect = true;
            if (selectedAnswers.length == correctAnswersForQuestion.size()) {
                for (Answer correctAnswer : correctAnswersForQuestion) {
                    boolean found = false;
                    for (String selectedAnswerId : selectedAnswers) {
                        if (correctAnswer.getId().toString().equals(selectedAnswerId)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        allCorrect = false;
                        break;
                    }
                }
            } else {
                allCorrect = false;
            }

            if (allCorrect) {
                correctAnswers++;
            }
        }

        model.addAttribute("quiz", quiz);
        model.addAttribute("questions", questions);
        model.addAttribute("result", "You got " + correctAnswers + " out of " + totalQuestions + " questions correct.");

        return "quiz-detail";
    }
}