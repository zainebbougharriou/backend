package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "niveaux")
public class Niveaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNiveau;

    @Column(name = "nomNiveau")
    private String nomNiveau;

    @Column(name = "imageNiveau")
    private String imageNiveau;

    // Association avec la classe Categorie
    @ManyToOne
    @JoinColumn(name = "idCategorie", nullable = false)
    private Categorie categorie;

	public Integer getIdNiveau() {
		return idNiveau;
	}

	public void setIdNiveau(Integer idNiveau) {
		this.idNiveau = idNiveau;
	}

	public String getNomNiveau() {
		return nomNiveau;
	}

	public void setNomNiveau(String nomNiveau) {
		this.nomNiveau = nomNiveau;
	}

	public String getImageNiveau() {
		return imageNiveau;
	}

	public void setImageNiveau(String imageNiveau) {
		this.imageNiveau = imageNiveau;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		return "Niveaux [idNiveau=" + idNiveau + ", nomNiveau=" + nomNiveau + ", imageNiveau=" + imageNiveau
				+ ", categorie=" + categorie + "]";
	}
    
    
}
