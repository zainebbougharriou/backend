package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "idCategorie")
	private int idCategorie;
	
	@Column(name = "nomCategorie")
	private String nomCategorie;

	@Column(name= "imageCategorie", columnDefinition = "LONGTEXT")
	private String imageCategorie;


	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}

	public String getNomCategorie() {
		return nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public String getImageCategorie() {
		return imageCategorie;
	}

	public void setImageCategorie(String imageCategorie) {
		this.imageCategorie = imageCategorie;
	}

	@Override
	public String toString() {
		return "Categorie [idCategorie=" + idCategorie + ", nomCategorie=" + nomCategorie + ", imageCategorie="
				+ imageCategorie + "]";
	}

	
	
	
	
	
	
}
