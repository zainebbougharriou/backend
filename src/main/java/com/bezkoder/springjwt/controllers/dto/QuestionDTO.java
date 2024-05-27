package com.bezkoder.springjwt.controllers.dto;
import com.bezkoder.springjwt.models.Proposition;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionDTO {
    private  String texteQuestion;
    private  String reponseCorrect;
    private  List<String> texteProp;


    public QuestionDTO() {
    }

    public static QuestionDTO map(List<Proposition> propositionList) {
        if(propositionList == null || propositionList.isEmpty()){
            return null;
        }
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTexteQuestion(propositionList.get(0).getQuestion().getTexteQuestion());
        questionDTO.setReponseCorrect(propositionList.get(0).getQuestion().getReponseCorrect());
        questionDTO.setTexteProp(propositionList.stream().map(Proposition::getTexteProp).collect(Collectors.toList()));

        return questionDTO ;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public String getReponseCorrect() {
        return reponseCorrect;
    }

    public void setReponseCorrect(String reponseCorrect) {
        this.reponseCorrect = reponseCorrect;
    }

    public List<String> getTexteProp() {
        return texteProp;
    }

    public void setTexteProp(List<String> texteProp) {
        this.texteProp = texteProp;
    }
}
