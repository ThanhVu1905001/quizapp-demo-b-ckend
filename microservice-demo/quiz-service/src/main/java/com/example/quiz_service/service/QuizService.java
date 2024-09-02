package com.example.quiz_service.service;

import com.example.quiz_service.dao.QuizDAO;
import com.example.quiz_service.feign.QuizInterface;
import com.example.quiz_service.model.Question;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Quiz;
import com.example.quiz_service.model.Response;
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
    QuizInterface quizInterface;
    public Quiz createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);
        return quiz;
    }

    public List<QuestionWrapper> getQuizById(Integer id){
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Integer> questionList = quiz.get().getQuestions();

        return (List<QuestionWrapper>) quizInterface.getQuestionsFromId(questionList);
    }

    public Integer calculateResult(Integer id, List<Response> responses) {
        return quizInterface.getScore(responses).getBody();
    }
}
