package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Quiz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "idQuiz")
	private int idQuiz;
	
	@Column(name = "nomQuiz")
	private String nomQuiz;
	
	@ManyToOne(optional = false) // Champ obligatoire
    @JoinColumn(name = "idCategorie", referencedColumnName = "idCategorie")
    private Categorie categorie;
	@ManyToOne(optional = false) // Champ obligatoire
	@JoinColumn(name = "idNiveau", referencedColumnName = "idNiveau")
	private Niveaux niveaux;
	public int getIdQuiz() {
		return idQuiz;
	}

	public void setIdQuiz(int idQuiz) {
		this.idQuiz = idQuiz;
	}

	public String getNomQuiz() {
		return nomQuiz;
	}

	public void setNomQuiz(String nomQuiz) {
		this.nomQuiz = nomQuiz;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Niveaux getNiveaux() {
		return niveaux;
	}

	public void setNiveaux(Niveaux niveaux) {
		this.niveaux = niveaux;
	}

	@Override
	public String toString() {
		return "Quiz [idQuiz=" + idQuiz + ", nomQuiz=" + nomQuiz + ", categorie=" + categorie + "]";
	}


	
	
	
	
	
}
