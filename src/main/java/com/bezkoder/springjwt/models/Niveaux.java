package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "niveaux")
public class Niveaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNiveau;

    @Column(name = "nomNiveau")
    private String nomNiveau;

    @Column(name = "imageNiveau" , columnDefinition = "LONGTEXT" )
    private String imageNiveau;

	@Column(name = "priorityNiveau")
	private Integer priorityNiveau;

    // Association avec la classe Categorie


	public Integer getPriorityNiveau() {
		return priorityNiveau;
	}

	public void setPriorityNiveau(Integer priorityNiveau) {
		this.priorityNiveau = priorityNiveau;
	}

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



	@Override
	public String toString() {
		return "Niveaux [idNiveau=" + idNiveau + ", nomNiveau=" + nomNiveau + ", imageNiveau=" + imageNiveau
				+  "]";
	}
    
    
}
