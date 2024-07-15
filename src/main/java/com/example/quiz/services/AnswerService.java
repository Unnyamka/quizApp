package com.example.quiz.services;

import com.example.quiz.entity.Answer;
import com.example.quiz.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public Answer save(Answer answer){
        return answerRepository.save(answer);
    }

    public List<Answer> findAll(){
        return answerRepository.findAll();
    }

    public List<Answer> findByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public List<Answer> findCorrectAnswersByQuestionId(Long questionId) {
        return answerRepository.findByQuestionIdAndCorrectTrue(questionId);
    }
}
