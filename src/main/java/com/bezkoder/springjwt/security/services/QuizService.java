package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.repository.NiveauxRepository;
import com.bezkoder.springjwt.repository.QuizRepository;
import com.bezkoder.springjwt.repository.CategorieRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
	//defining the business logic  

	@Autowired  
	QuizRepository qR; 
	
	CategorieRepository cR;
	NiveauxRepository nR;

//getting all articles record by using the method findaAll() of CrudRepository  
public List<Quiz> getAllQuiz()   
{  
	List<Quiz> quizList = new ArrayList<Quiz>();
	qR.findAll().forEach(quizList::add);
	quizList.sort(Comparator.comparing(Quiz::getNomQuiz));

	return quizList;
}  

//getting a specific record by using the method findById() of CrudRepository  
public Quiz getQuizById(int id)   
{  
	return qR.findById(id).get();  
}  


//saving a specific record by using the method save() of CrudRepository  
public Quiz saveOrUpdate(Quiz r)   
{  
	return qR.save(r);  
} 

//deleting a specific record by using the method deleteById() of CrudRepository  
public void delete(int id)   
{  
	qR.deleteById(id);  
} 

public List<Quiz> getAllQuizByCategorie(int idCategorie) {
    List<Quiz> quizzes = new ArrayList<Quiz>();
    qR.findAllByCategorie(cR.findById(idCategorie).get()).forEach(f -> quizzes.add(f));
    return quizzes;
}
	public List<Quiz> getAllQuizByNiveaux(int idNiveau) {
		List<Quiz> quizzes = new ArrayList<Quiz>();
		qR.findAllByNiveaux(nR.findById(idNiveau).get()).forEach(f -> quizzes.add(f));
		return quizzes;
	}

}