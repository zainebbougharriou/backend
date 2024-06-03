package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.controllers.dto.QuestionDTO;
import com.bezkoder.springjwt.models.Proposition;
import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PropositionService {
    @Autowired
    PropositionRepository pR;
    @Autowired
    QuizService quizService;

    //getting all articles record by using the method findaAll() of CrudRepository
    public List<Proposition> getAllProposition()
    {
        List<Proposition> Proposition = new ArrayList<Proposition>();
        pR.findAll().forEach(r -> Proposition.add(r));
        return Proposition;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public Proposition getPropositionById(int id)
    {
        return pR.findById(id).get();
    }


    //saving a specific record by using the method save() of CrudRepository
    public Proposition saveOrUpdate(Proposition r)
    {
        return pR.save(r);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        pR.deleteById(id);
    }

    public QuestionDTO getPropositionByIdQuestion(int id) {

        List<Proposition> propositionList = pR.findByQuestionIdQuestion(id);

      return QuestionDTO.map(propositionList) ;

    }

    public  List<Proposition> getPropositionByIdQuestionAsList(int id) {
        return pR.findByQuestionIdQuestion(id);

    }

    public List<QuestionDTO> getQuestionAndPropositionByQuiz(int idQuiz) {

        Quiz quiz = quizService.getQuizById(idQuiz) ;
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        quiz.getQuestions().forEach(question -> {
            QuestionDTO questionDTO = new QuestionDTO() ;
              List<Proposition> propositionList = pR.findByQuestionIdQuestion(question.getIdQuestion());
              questionDTO = QuestionDTO.map(propositionList);
            questionDTOS.add(questionDTO);
        });
        return questionDTOS;
    }
}
