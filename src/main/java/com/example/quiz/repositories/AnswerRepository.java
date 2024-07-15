package com.example.quiz.repositories;

import com.example.quiz.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByQuestionId(Long questionId);
    List<Answer> findByQuestionIdAndCorrectTrue(Long questionId);
}
