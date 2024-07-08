package com.quiz.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.app.model.Question;
import com.quiz.app.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController 
{
    @Autowired
     private QuestionService questionService;

	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions()
	{

		    System.out.println("in controller");
//		    ResponseEntity<List<Question>> questions = questionService.getAllQuestions();
//		    System.out.println("Questions List Size: " + questions.size());
//		    System.out.println("Questions List Contents: " + questions);
		    return  questionService.getAllQuestions() ;
	}
	
	
	@GetMapping("/category/{cat}")
	public  ResponseEntity<List<Question>> getByCategory(@PathVariable("cat") String category)
	{
		    return questionService.getByCategory(category);
	}

	@PostMapping("/add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) 
	{
		System.out.println("Questions added Contents: " + question);
		return questionService.addQuestion(question);
	}
	
}
