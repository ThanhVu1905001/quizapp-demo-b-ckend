package com.thanh.demo.quizapp.dao;

import com.thanh.demo.quizapp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
