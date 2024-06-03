package com.bezkoder.springjwt.controllers;

import java.util.List;

import com.bezkoder.springjwt.controllers.dto.QuestionDTO;
import com.bezkoder.springjwt.controllers.dto.UpdateQuestionDTO;
import com.bezkoder.springjwt.models.Proposition;
import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.security.services.PropositionService;
import com.bezkoder.springjwt.security.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/QuestionProposition/quiz/{idQuiz}")
	private List<QuestionDTO> getQuestionAndPropositionByQuiz(@PathVariable("idQuiz") int idQuiz) {
		return propositionService.getQuestionAndPropositionByQuiz(idQuiz);
	}


	//creating a delete mapping that deletes a specified article
		@DeleteMapping("/Question/{id}")  
		private void deleteQuestion(@PathVariable("id") int id)   
		{
			Question question = qs.getQuestionById(id);
			 Quiz quiz =question.getQuiz();
			 quiz.getQuestions().remove(question);
			 quizService.saveOrUpdate(quiz);
			List<Proposition> propositions = propositionService.getPropositionByIdQuestionAsList(id);
			propositions.forEach(proposition -> propositionService.delete(proposition.getIdProp()));
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
	@PostMapping("/Question")
	private void update(@RequestBody UpdateQuestionDTO questionDTO) {

		Question question = qs.getQuestionById(questionDTO.getId());
		if(!StringUtils.isEmpty(questionDTO.getTextQ())){
			question.setTexteQuestion(questionDTO.getTextQ());
		}
		 qs.saveOrUpdate(question);
	}
	@GetMapping("/quizByIdQuiz/{idQuiz}")
	private List<Question> getAllQuestionByIdQuiz(@PathVariable("idQuiz") int idQuiz)
	{
		return qs.getAllQuestionByQuiz(idQuiz);
	}

	}