package com.bezkoder.springjwt.controllers;
import com.bezkoder.springjwt.controllers.dto.UpdatePropositionDTO;
import com.bezkoder.springjwt.models.Proposition;
import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.security.services.PropositionService;
import com.bezkoder.springjwt.security.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/proposition")
@CrossOrigin(origins = "*")

public class PropositionController {
    @Autowired
    PropositionService ps;

    @Autowired
    QuestionService questionService;
    //creating a get mapping that retrieves all the Article detail from the database
    @GetMapping("/Proposition")
    private List<Proposition> getAllProposition()
    {
        return ps.getAllProposition();
    }

    //creating a get mapping that retrieves the detail of a specific article
    @GetMapping("/Proposition/{id}")
    private Proposition getProposition(@PathVariable("id") int id)
    {
        return ps.getPropositionById(id);
    }

    //creating a delete mapping that deletes a specified article
    @DeleteMapping("/Proposition/{id}")
    private void deleteProposition(@PathVariable("id") int id)
    {
        ps.delete(id);
    }

    //create new article
    @PostMapping("/Proposition/{questionId}")
    private Integer saveProposition(@PathVariable("questionId") int questionId , @RequestBody Proposition c)
    {

        Question question = questionService.getQuestionById(questionId);
        if(question == null){
            return null ;
        }
        c.setQuestion(question);
        Proposition proposition = ps.saveOrUpdate(c);
        return question.getIdQuestion();

    }

    //creating put mapping that updates the article detail
    @PostMapping("/Proposition")
    private void update(@RequestBody UpdatePropositionDTO updatePropositionDTO) {

        Proposition proposition = ps.getPropositionById(updatePropositionDTO.getId());
        if(!StringUtils.isEmpty(updatePropositionDTO.getTextProposition())){
            proposition.setTexteProp(updatePropositionDTO.getTextProposition());
        }
        ps.saveOrUpdate(proposition);
    }

}
