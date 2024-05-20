package com.bezkoder.springjwt.models;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

	public enum ERole {
		ROLE_DEVELOPPEUR(0), ROLE_ADMINISTRATEUR(1);
		private int value;
		ERole(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		public static ERole valueOf(int value) {
			switch(value) {
				case 0: return ROLE_DEVELOPPEUR;
				case 1: return ROLE_ADMINISTRATEUR;
				default: return null;
			}
		}
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 20)
	private ERole name;

	@Column(length = 20)
	private String label;

	public Role() {

	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}