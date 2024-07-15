package com.example.quiz.services;

import com.example.quiz.entity.Question;
import com.example.quiz.repositories.QuestionRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepositroy questionRepositroy;

    public Question save(Question question){
        return questionRepositroy.save(question);
    }

    public List<Question> findAlL(){
        return questionRepositroy.findAll();
    }

    public List<Question> findByQuizId(Long quizId) {
        return questionRepositroy.findByQuizId(quizId);
    }
}
