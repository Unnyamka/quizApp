package com.example.quiz.repositories;

import com.example.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepositroy extends JpaRepository<Question,Long> {
}
