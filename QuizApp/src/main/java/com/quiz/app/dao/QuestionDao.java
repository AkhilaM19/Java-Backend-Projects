package com.quiz.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.app.model.Question;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface  QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);

	// nativeQuery = true
    @Query(value = "SELECT * FROM question q WHERE q.category=:category ORDER BY RAND() LIMIT :noOfQns", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(@Param("category") String category,@Param("noOfQns") int noOfQns);
 
}
