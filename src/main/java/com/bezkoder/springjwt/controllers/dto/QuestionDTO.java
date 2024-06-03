package com.bezkoder.springjwt.controllers.dto;
import com.bezkoder.springjwt.models.Proposition;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDTO {
    private  Integer idQuestion;
    private  String texteQuestion;
    private  String reponseCorrect;
    private  List<String> texteProp;
    private  List<Integer> idsTexteProp;


    public QuestionDTO() {
    }

    public static QuestionDTO map(List<Proposition> propositionList) {
        if(propositionList == null || propositionList.isEmpty()){
            return null;
        }
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setIdQuestion(propositionList.get(0).getQuestion().getIdQuestion());
        questionDTO.setTexteQuestion(propositionList.get(0).getQuestion().getTexteQuestion());
        questionDTO.setReponseCorrect(propositionList.get(0).getQuestion().getReponseCorrect());
        questionDTO.setTexteProp(
                propositionList.stream()
                        .sorted(Comparator.comparing(Proposition::getIdProp))
                        .map(Proposition::getTexteProp)
                        .collect(Collectors.toList())
        );

        questionDTO.setIdsTexteProp(
                propositionList.stream()
                        .sorted(Comparator.comparing(Proposition::getIdProp))
                        .map(Proposition::getIdProp)
                        .collect(Collectors.toList())
        );

        return questionDTO ;
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public List<Integer> getIdsTexteProp() {
        return idsTexteProp;
    }

    public void setIdsTexteProp(List<Integer> idsTexteProp) {
        this.idsTexteProp = idsTexteProp;
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
