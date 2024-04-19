package com.bezkoder.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "proposition")

public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProp;

    @Column(name = "texteProp")
    private String texteProp;

    @ManyToOne
    @JoinColumn(name = "idQuestion", nullable = false)
    private Question question;

    public int getIdProp() {
        return idProp;
    }

    public void setIdProp(int idProp) {
        this.idProp = idProp;
    }

    public String getTexteProp() {
        return texteProp;
    }

    public void setTexteProp(String texteProp) {
        this.texteProp = texteProp;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}

