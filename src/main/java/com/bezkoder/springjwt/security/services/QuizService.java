package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Proposition;
import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.repository.NiveauxRepository;
import com.bezkoder.springjwt.repository.QuizRepository;
import com.bezkoder.springjwt.repository.CategorieRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizService {
	//defining the business logic  

	@Autowired  
	QuizRepository qR;
	@Autowired
	CategorieRepository cR;
	@Autowired
	NiveauxRepository nR;
	@Autowired
	QuestionService questionService;
	@Autowired
	PropositionService propositionService;



	public void updateQuizQuestionReferences(int quizId) {
		List<Question> questions = questionService.getAllQuestionByQuiz(quizId);
		questions.forEach(qu -> {
			List<Proposition> propositions = propositionService.getPropositionByIdQuestionAsList(qu.getIdQuestion());
			propositions.forEach(proposition -> propositionService.delete(proposition.getIdProp()));
			removeQuestionFromQuiz(quizId , qu.getIdQuestion());
		});
	}

	private void removeQuestionFromQuiz(int quizId, int questionId) {
		Quiz quiz = qR.findById(quizId).get();
		if (quiz != null) {
			List<Question> questions = quiz.getQuestions();
			questions.removeIf(question -> question.getIdQuestion() == questionId);
			quiz.setQuestions(questions);
			qR.save(quiz);
			questionService.delete(questionId);

		}
	}


//getting all articles record by using the method findaAll() of CrudRepository  
public List<Quiz> getAllQuiz()   
{  
	List<Quiz> quizList = new ArrayList<Quiz>();
	qR.findAll().forEach(quizList::add);
	quizList.sort(Comparator.comparing(Quiz::getNomQuiz));

	return quizList;
}  

	public List<Quiz> getAllQuizByCategoryIdAndNiveaux(int idCategory, int idLevel) {
		List<Quiz> quizList = new ArrayList<>();
		qR.findAll().forEach(quizList::add);

		return quizList.stream()
				.filter(q -> q.getCategorie().getIdCategorie() == idCategory)
				.filter(q -> q.getNiveaux().getIdNiveau() == idLevel)
				.sorted(Comparator.comparing(Quiz::getNomQuiz))
				.collect(Collectors.toList());
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