package com.thanh.demo.quizapp.dao;

import com.thanh.demo.quizapp.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String cat);
    @Query(value = "SELECT * FROM question q WHERE q.category=:cat ORDER BY RADOM() LIMIT :num", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String cat, int num);
}
