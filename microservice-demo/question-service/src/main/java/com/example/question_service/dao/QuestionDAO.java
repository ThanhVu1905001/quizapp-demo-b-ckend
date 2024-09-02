package com.example.question_service.dao;

import com.example.question_service.model.Question;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String cat);
    @Query(value = "SELECT q.id FROM question q WHERE q.category=:cat ORDER BY RADOM() LIMIT :num", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String cat, int num);
}
