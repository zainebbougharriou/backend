package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuestion;

    @Column(name = "texteQuestion")
    private String texteQuestion;



    @Column(name = "reponseCorrect")
    private String reponseCorrect; // The correct answer for the question

	public int getIdQuestion() {
		return idQuestion;
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

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
		
	}
   
}
