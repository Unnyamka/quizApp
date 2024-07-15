package com.example.quiz.services;

import com.example.quiz.entity.Quiz;
import com.example.quiz.repositories.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public Quiz save(Quiz quiz){
        return quizRepository.save(quiz);
    }

    public List<Quiz> findAll(){
        return quizRepository.findAll();
    }

    public Quiz findById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }
}
