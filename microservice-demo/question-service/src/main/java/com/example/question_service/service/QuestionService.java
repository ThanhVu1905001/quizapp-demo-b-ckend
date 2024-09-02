package com.example.question_service.service;


import com.example.question_service.dao.QuestionDAO;
import com.example.question_service.model.Question;
import com.example.question_service.model.QuestionWrapper;
import com.example.question_service.model.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Integer> getQuestionForQuiz(String categoryName, Integer numberQuestions) {
        List<Integer> questions = questionDAO.findRandomQuestionsByCategory(categoryName, numberQuestions);
        return questions;
    }

    public List<QuestionWrapper> getQuestionsFromId(List<Integer> questionIds) {
        return questionIds.stream()
            .map(i -> {
                Question question = questionDAO.findById(i).orElseThrow(() -> new RuntimeException("Question not found with id: " + i));
                return new QuestionWrapper(
                    question.getId(),
                    question.getQuestionTitle(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4()
                );
            })
            .collect(Collectors.toList());
    }

    public Integer getScore(List<Response> responses) {
        return (int) IntStream.range(0, responses.size())
            .filter(i -> responses.get(i).getResponse().equals(questionDAO.findById(i).get().getSolution()))
            .count();
    }
}
