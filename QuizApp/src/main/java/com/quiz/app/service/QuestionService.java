package com.quiz.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.app.dao.QuestionDao;
import com.quiz.app.model.Question;

@Service
public class QuestionService {

	private final QuestionDao questiondao;

//	@Autowired
	public QuestionService(QuestionDao questiondao) {
		this.questiondao = questiondao;
	}

	public ResponseEntity<List<Question>> getAllQuestions() {

		System.out.println("in service");
		try {
		return new ResponseEntity<>(questiondao.findAll(),HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getByCategory(String category) {
        try {
            return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
			
		questiondao.save(question);
		try {
		return new ResponseEntity<>("success",HttpStatus.CREATED);
		}catch(Exception e){
            e.printStackTrace();
        }
		return new ResponseEntity<>("failed", HttpStatus.NOT_MODIFIED);
		
	}
}
