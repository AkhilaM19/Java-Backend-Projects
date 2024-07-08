package com.quiz.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.dao.QuestionDao;
import com.quiz.app.dao.QuizDao;
import com.quiz.app.model.Question;
import com.quiz.app.model.QuestionWrapper;
import com.quiz.app.model.Quiz;
import com.quiz.app.model.Response;

@Service
public class QuizService {

	
	QuizDao quizdao;
	QuestionDao questiondao;
	
	@Autowired	
	public QuizService(QuizDao quizdao, QuestionDao questiondao) {
		this.quizdao = quizdao;
		this.questiondao = questiondao;
	}
	public ResponseEntity<String> createQuiz(String category, int noOfQns, String title) {
		// TODO Auto-generated method stub
		List<Question> questions =questiondao.findRandomQuestionByCategory(category,noOfQns);
		Quiz q=new Quiz();
		q.setTitle(title);
		q.setQuestions(questions);
		quizdao.save(q);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz =  quizdao.findById(id);
		List<Question> qnsFromBD = quiz.get().getQuestions();
		List<QuestionWrapper> qnsForUser = new ArrayList<>();
		for (Question q :qnsFromBD)
		{
			QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			qnsForUser.add(qw);
		}
		return new ResponseEntity<>(qnsForUser, HttpStatus.OK );
	}
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		  Quiz quiz = quizdao.findById(id).get();
		  System.out.println("the quiz obj " + quiz);
		  List<Question> questions = quiz.getQuestions();
		  System.out.println("questions: "+ questions);
		  System.out.println(responses);
		  int right = 0;
	      int i = 0;
	      for(Response response : responses){
	        if(response.getResponse().equals(questions.get(i).getRightAnswer()))
	        {
	            System.out.println("the answers "+ response.getResponse() + " "+ questions.get(i).getRightAnswer());
	            right++;
	        }
	         i++;
	      }
	      System.out.println(right);
	      return new ResponseEntity<>(right, HttpStatus.OK);
	}
}
