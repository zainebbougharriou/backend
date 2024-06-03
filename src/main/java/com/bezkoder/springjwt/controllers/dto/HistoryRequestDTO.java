package com.bezkoder.springjwt.controllers.dto;

public class HistoryRequestDTO {
    private Integer idQuiz;
    private Integer temps;
    private String score;

    public HistoryRequestDTO() {
    }

    public HistoryRequestDTO(Integer idQuiz, String score , Integer temps) {
        this.idQuiz = idQuiz;
        this.score = score;
        this.temps = temps;
    }

    public Integer getTemps() {
        return temps;
    }

    public void setTemps(Integer temps) {
        this.temps = temps;
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