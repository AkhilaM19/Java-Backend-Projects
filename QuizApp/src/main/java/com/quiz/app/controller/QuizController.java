package com.quiz.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.model.Question;
import com.quiz.app.model.QuestionWrapper;
import com.quiz.app.model.Response;
import com.quiz.app.service.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("quiz")
public class QuizController {

	@Autowired
	QuizService quizservice;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noOfQns ,@RequestParam String title ) {
		
		System.out.println("in QuizController");
		return quizservice.createQuiz(category,noOfQns,title);
//	    return new ResponseEntity<>("created successfully", HttpStatus.CREATED);
	}

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizservice.getQuizQuestions(id);
    }
    
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
	
    	return quizservice.calculateResult(id, responses);
    }

}
