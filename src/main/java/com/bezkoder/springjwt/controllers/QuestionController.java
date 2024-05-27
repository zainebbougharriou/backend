package com.bezkoder.springjwt.controllers;

import java.util.List;

import com.bezkoder.springjwt.controllers.dto.QuestionDTO;
import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.security.services.PropositionService;
import com.bezkoder.springjwt.security.services.QuizService;
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

import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.security.services.QuestionService;


//mark class as Controller  
@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "*")
public class QuestionController {
	//autowire the ArticleService class  
		@Autowired  
		QuestionService qs;
		@Autowired
		PropositionService propositionService;
		@Autowired
		QuizService quizService;
		//creating a get mapping that retrieves all the Article detail from the database   
		@GetMapping("/Question")
		private List<Question> getAllQuestion()   
		{  
			return qs.getAllQuestion();  
		}  

		//creating a get mapping that retrieves the detail of a specific article  
		@GetMapping("/Question/{id}")  
		private Question getQuestion(@PathVariable("id") int id)   
		{  
			return qs.getQuestionById(id);  
		}

		@GetMapping("/QuestionProposition/{id}")
		private QuestionDTO getQuestionAndProposition(@PathVariable("id") int id) {
		return propositionService.getPropositionByIdQuestion(id);
	}


	//creating a delete mapping that deletes a specified article
		@DeleteMapping("/Question/{id}")  
		private void deleteQuestion(@PathVariable("id") int id)   
		{  
			qs.delete(id);  
		}

		//create new article
		@PostMapping("/Question/{quizId}")
		private Integer saveQuestion(@PathVariable("quizId") int quizId , @RequestBody Question c)
		{
			Quiz quiz = quizService.getQuizById(quizId);
			if(quiz == null){
				return null ;
			}
			c.setQuiz(quiz);
			Question question = qs.saveOrUpdate(c);
			return question.getIdQuestion();
		}

	//creating put mapping that updates the article detail
		@PutMapping("/Question/{id}")
		private Question update(@PathVariable int id, @RequestBody Question c) {
		    // Définissez l'identifiant de la catégorie avec la valeur de l'identifiant du chemin
		    c.setIdQuestion(id);
		    // Met à jour la catégorie existante
		    return qs.saveOrUpdate(c);
		}
	@GetMapping("/quizByIdQuiz/{idQuiz}")
	private List<Question> getAllQuestionByIdQuiz(@PathVariable("idQuiz") int idQuiz)
	{
		return qs.getAllQuestionByQuiz(idQuiz);
	}

	}