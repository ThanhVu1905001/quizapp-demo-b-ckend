package com.thanh.demo.quizapp.service;

import com.thanh.demo.quizapp.dao.QuestionDAO;
import com.thanh.demo.quizapp.dao.QuizDAO;
import com.thanh.demo.quizapp.model.Question;
import com.thanh.demo.quizapp.model.QuestionWrapper;
import com.thanh.demo.quizapp.model.Quiz;
import com.thanh.demo.quizapp.model.Response;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;

    public Quiz createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDAO.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return quiz;
    }

    public List<QuestionWrapper> getQuizById(Integer id){
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questionList = quiz.get().getQuestions();

        return questionList.stream()
            .map(q -> new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(),
                q.getOption2(), q.getOption3(), q.getOption4()))
            .collect(Collectors.toList());
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        return (int) IntStream.range(0, responses.size())
            .filter(i -> responses.get(i).getResponse().equals(questions.get(i).getSolution()))
            .count();
    }
}
