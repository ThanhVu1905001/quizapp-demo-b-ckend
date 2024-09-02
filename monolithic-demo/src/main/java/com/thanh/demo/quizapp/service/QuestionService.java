package com.thanh.demo.quizapp.service;

import com.thanh.demo.quizapp.dao.QuestionDAO;
import com.thanh.demo.quizapp.model.Question;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    public List<Question> getAllQuestions(){
        return questionDAO.findAll();
    }
    public List<Question> getQuestionsByCategory(String cat){
        return questionDAO.findByCategory(cat);
    }

    public Question addQuestion(Question question){
        return questionDAO.save(question);
    }
}
