package com.bezkoder.springjwt.controllers.dto;
import com.bezkoder.springjwt.models.Historique;

public class HistoryDTO {
    private  String userName;
    private  String score;
    private  String dateHeure;
    private  String nomQuiz;
    private  String nomCategorie;
    private  String nomNiveau;
    private  String imageNiveau;
    private  String imageCategorie;


    public HistoryDTO() {
    }

    public static HistoryDTO map(Historique historiques) {
        if(historiques == null ){
            return null;
        }
        HistoryDTO questionDTO = new HistoryDTO();
            questionDTO.setDateHeure(historiques.getFormattedDateHeure());
            questionDTO.setUserName(historiques.getUtilisateur().getUsername());
            questionDTO.setScore(historiques.getScore());
            questionDTO.setNomCategorie(historiques.getQuiz().getCategorie().getNomCategorie());
            questionDTO.setNomNiveau(historiques.getQuiz().getNiveaux().getNomNiveau());
            questionDTO.setNomQuiz(historiques.getQuiz().getNomQuiz());
            questionDTO.setImageCategorie(historiques.getQuiz().getCategorie().getImageCategorie());
            questionDTO.setImageNiveau(historiques.getQuiz().getNiveaux().getImageNiveau());

        return questionDTO ;
    }

    public String getImageNiveau() {
        return imageNiveau;
    }

    public void setImageNiveau(String imageNiveau) {
        this.imageNiveau = imageNiveau;
    }

    public String getImageCategorie() {
        return imageCategorie;
    }

    public void setImageCategorie(String imageCategorie) {
        this.imageCategorie = imageCategorie;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(String dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getNomQuiz() {
        return nomQuiz;
    }

    public void setNomQuiz(String nomQuiz) {
        this.nomQuiz = nomQuiz;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getNomNiveau() {
        return nomNiveau;
    }

    public void setNomNiveau(String nomNiveau) {
        this.nomNiveau = nomNiveau;
    }
}
