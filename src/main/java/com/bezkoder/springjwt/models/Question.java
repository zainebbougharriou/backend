package com.bezkoder.springjwt.models;

import javax.persistence.*;

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

	@ManyToOne(optional = false) // Champ obligatoire
	@JoinColumn(name = "idQuiz", referencedColumnName = "idQuiz")
	private Quiz quiz;

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;

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

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}


   
}
