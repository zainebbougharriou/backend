package com.bezkoder.springjwt.controllers.dto;

import com.bezkoder.springjwt.models.Quiz;

public class QuizDTO {
    private  Integer idQuiz;
    private  String nomQuiz;
    private  String categorieName;
    private  String categorieImage;
    private  String nameNiveau;
    private  String imageNiveau;
    private Integer countQuestions;

    public QuizDTO() {
    }

    public static QuizDTO map(Quiz quiz) {
        if(quiz == null){
            return null;
        }
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setIdQuiz( quiz.getIdQuiz());
        quizDTO.setNomQuiz( quiz.getNomQuiz());
        quizDTO.setCategorieImage(quiz.getCategorie().getImageCategorie());
        quizDTO.setCategorieName(  quiz.getCategorie().getNomCategorie());
        quizDTO.setNameNiveau( quiz.getNiveaux().getNomNiveau());
        quizDTO.setImageNiveau( quiz.getNiveaux().getImageNiveau());
        quizDTO.setCountQuestions(  quiz.getQuestions().size());

        return quizDTO ;
    }

    public Integer getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(Integer idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getNomQuiz() {
        return nomQuiz;
    }

    public void setNomQuiz(String nomQuiz) {
        this.nomQuiz = nomQuiz;
    }

    public String getCategorieName() {
        return categorieName;
    }

    public void setCategorieName(String categorieName) {
        this.categorieName = categorieName;
    }

    public String getCategorieImage() {
        return categorieImage;
    }

    public void setCategorieImage(String categorieImage) {
        this.categorieImage = categorieImage;
    }

    public String getNameNiveau() {
        return nameNiveau;
    }

    public void setNameNiveau(String nameNiveau) {
        this.nameNiveau = nameNiveau;
    }

    public String getImageNiveau() {
        return imageNiveau;
    }

    public void setImageNiveau(String imageNiveau) {
        this.imageNiveau = imageNiveau;
    }

    public Integer getCountQuestions() {
        return countQuestions;
    }

    public void setCountQuestions(Integer countQuestions) {
        this.countQuestions = countQuestions;
    }
}
