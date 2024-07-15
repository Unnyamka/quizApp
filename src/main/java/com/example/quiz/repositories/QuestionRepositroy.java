package com.example.quiz.repositories;

import com.example.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepositroy extends JpaRepository<Question,Long> {
    public List<Question> findByQuizId(Long quizId);
}
