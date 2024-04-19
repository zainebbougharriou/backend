package com.bezkoder.springjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.bezkoder.springjwt.models.Categorie;
import com.bezkoder.springjwt.models.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, Integer> {
	public java.lang.Iterable<Quiz> findAllByCategorie(Categorie R);
}
