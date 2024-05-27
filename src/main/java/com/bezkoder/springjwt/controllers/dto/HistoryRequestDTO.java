package com.bezkoder.springjwt.controllers.dto;

public class HistoryRequestDTO {
    private Integer idQuiz;
    private String score;

    public HistoryRequestDTO() {
    }

    public HistoryRequestDTO(Integer idQuiz, String score) {
        this.idQuiz = idQuiz;
        this.score = score;
    }


    public int getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(int idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}