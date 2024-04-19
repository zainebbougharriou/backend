package com.bezkoder.springjwt.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "historique")
public class Historique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorique;

    @Column(name = "score")
    private int score;

    @Column(name = "dateHeure")
    private LocalDateTime dateHeure;

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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public LocalDateTime getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(LocalDateTime dateHeure) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.dateHeure.format(formatter);
    }

    // Utilitaire pour obtenir le score en tant que chaîne
    public String getFormattedScore() {
        return String.format("%d%%", this.score);
    }

    // Utilitaire pour obtenir le temps sous forme de "X minutes, Y secondes"
    public String getFormattedTemps() {
        int minutes = this.temps / 60;
        int seconds = this.temps % 60;
        return String.format("%d minutes, %d secondes", minutes, seconds);
    }

    
}
