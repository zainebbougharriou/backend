package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Categorie;
import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.security.services.QuizService;


//mark class as Controller  
@RestController
@RequestMapping("/qui")
@CrossOrigin(origins = "*")

public class QuizController {
	//autowire the ArticleService class  
	@Autowired  
	QuizService qs; 
	//creating a get mapping that retrieves all the Article detail from the database   
	@GetMapping("/Quiz")
	private List<Quiz> getAllQuiz()   
	{  
		return qs.getAllQuiz();  
	}  

	//creating a get mapping that retrieves the detail of a specific article  
	@GetMapping("/Quiz/{id}")  
	private Quiz getQuiz(@PathVariable("id") int id)   
	{  
		return qs.getQuizById(id);  
	}  

	//creating a delete mapping that deletes a specified article  
	@DeleteMapping("/Quiz/{id}")  
	private void deleteQuiz(@PathVariable("id") int id)   
	{  
		qs.delete(id);  
	} 

	//create new article
	@PostMapping("/Quiz")  
	private Quiz saveQuiz(@RequestBody Quiz c)   
	{  
		  
		return  qs.saveOrUpdate(c);
	} 

	//creating put mapping that updates the article detail
	@PutMapping("/Quiz/{id}")
	private Quiz update(@PathVariable int id, @RequestBody Quiz c) {
	    // Définissez l'identifiant de la catégorie avec la valeur de l'identifiant du chemin
	    c.setIdQuiz(id);
	    // Met à jour la catégorie existante
	    return qs.saveOrUpdate(c);
	}
	@GetMapping("/quizByIdCategorie/{idCategorie}")
	private List<Quiz> getAllQuizByIdCategorie(@PathVariable("idCategorie") int idCategorie)   
	{  
		return qs.getAllQuizByCategorie(idCategorie);  
	}
	@GetMapping("/quizByIdNiveau/{idNiveau}")
	private List<Quiz> getAllQuizByIdNiveau(@PathVariable("idNiveau") int idNiveau)
	{
		return qs.getAllQuizByNiveaux(idNiveau);
	}
}