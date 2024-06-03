package com.bezkoder.springjwt.controllers;

import java.util.ArrayList;
import java.util.List;

import com.bezkoder.springjwt.controllers.dto.QuizDTO;
import com.bezkoder.springjwt.controllers.dto.request.QuizRequestDTO;
import com.bezkoder.springjwt.models.Categorie;
import com.bezkoder.springjwt.models.Niveaux;
import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.security.services.CategorieService;
import com.bezkoder.springjwt.security.services.NiveauxService;
import com.bezkoder.springjwt.security.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@Autowired
	QuestionService questionService;

	@Autowired
	CategorieService categorieService;

	@Autowired
	NiveauxService niveauxService;
	//creating a get mapping that retrieves all the Article detail from the database   
	@GetMapping("/Quiz")
	public List<QuizDTO> getAllQuiz() {
		List<QuizDTO> quizDTOs = new ArrayList<>();
		List<Quiz> quizzes = qs.getAllQuiz();
		for (Quiz quiz : quizzes) {
			quizDTOs.add(QuizDTO.map(quiz));
		}
		return quizDTOs;
	}

	@GetMapping("/Quiz/{idCategory}/{idLevel}")
	public List<QuizDTO> getAllQuiz(@PathVariable("idCategory") int idCategory ,@PathVariable("idLevel") int idLevel  ) {

		List<QuizDTO> quizDTOs = new ArrayList<>();
		List<Quiz> quizzes = qs.getAllQuizByCategoryIdAndNiveaux(idCategory , idLevel);
		for (Quiz quiz : quizzes) {
			quizDTOs.add(QuizDTO.map(quiz));
		}
		return quizDTOs;
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
	private Quiz saveQuiz(@RequestBody QuizRequestDTO quizRequestDTO)
	{

		Categorie categorie = categorieService.getCategoriesById(quizRequestDTO.getIdCategorie()) ;
		Niveaux niveaux = niveauxService.getNiveauxById(quizRequestDTO.getIdLevel()) ;

		Quiz quiz = new Quiz();
		quiz.setNomQuiz(quizRequestDTO.getNomQuiz());
		quiz.setNiveaux(niveaux);
		quiz.setCategorie(categorie);

		return  qs.saveOrUpdate(quiz);
	}

	@PostMapping("/setQuestion/{id}")
	private void saveQuiz(@PathVariable("id") int id , @RequestParam("idQuestion") Integer idQuestion)
	{
		if (idQuestion == null){
			return ;
		}
		Question question = questionService.getQuestionById(idQuestion);
			if(question == null){
				return ;
			}
		Quiz quiz = qs.getQuizById(id);
			if(quiz == null){
				return;
			}
			quiz.getQuestions().add(question);
		  qs.saveOrUpdate(quiz);
	}


	//creating put mapping that updates the article detail

	@PutMapping("/Quiz")
	private Quiz update(@RequestBody QuizRequestDTO quizRequestDTO)
	{

		Quiz quiz = qs.getQuizById(quizRequestDTO.getIdQuiz());

		Categorie categorie = categorieService.getCategoriesById(quizRequestDTO.getIdCategorie()) ;
		Niveaux niveaux = niveauxService.getNiveauxById(quizRequestDTO.getIdLevel()) ;

		quiz.setNomQuiz(quiz.getNomQuiz());
		quiz.setNiveaux(niveaux);
		quiz.setCategorie(categorie);

		return  qs.saveOrUpdate(quiz);
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