package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "historique")
public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorique;

    @Column(name = "score")
    private String score;

    @Column(name = "dateHeure")
    private Date dateHeure;

    @Column(name = "temps")
    private int temps;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUtilisateur")
    private User utilisateur;


    @ManyToOne
    @JoinColumn(name = "idQuiz")
    private Quiz quiz;

	public int getIdHistorique() {
		return idHistorique;
	}

	public void setIdHistorique(int idHistorique) {
		this.idHistorique = idHistorique;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public Date getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(Date dateHeure) {
		this.dateHeure = dateHeure;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps(int temps) {
		this.temps = temps;
	}

	public User getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(User utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}
	// Utilitaire pour formater la date et l'heure
    public String getFormattedDateHeure() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return formatter.format(this.dateHeure);
    }

    // Utilitaire pour obtenir le temps sous forme de "X minutes, Y secondes"
    public String getFormattedTemps() {
        int minutes = this.temps / 60;
        int seconds = this.temps % 60;
        return String.format("%d minutes, %d secondes", minutes, seconds);
    }

    
}
