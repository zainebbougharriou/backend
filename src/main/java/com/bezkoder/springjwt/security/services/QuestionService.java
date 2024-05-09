package com.bezkoder.springjwt.security.services;

import java.util.ArrayList;
import java.util.List;

import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired  
	QuestionRepository qR;

	QuizRepository qzR;
//getting all articles record by using the method findaAll() of CrudRepository  
public List<Question> getAllQuestion()   
{  
	List<Question> Question = new ArrayList<Question>();  
	qR.findAll().forEach(r -> Question.add(r));  
	return Question;  	
}  

//getting a specific record by using the method findById() of CrudRepository  
public Question getQuestionById(int id)   
{  
	return qR.findById(id).get();  
}  


//saving a specific record by using the method save() of CrudRepository  
public Question saveOrUpdate(Question r)   
{  
	return qR.save(r);  
} 

//deleting a specific record by using the method deleteById() of CrudRepository  
public void delete(int id)   
{  
	qR.deleteById(id);  
}
	public List<Question> getAllQuestionByQuiz(int idQuiz) {
		List<Question> questionList = new ArrayList<Question>();
		qR.findAllByQuiz(qzR.findById(idQuiz).get()).forEach(f -> questionList.add(f));
		return questionList;
	}

}
