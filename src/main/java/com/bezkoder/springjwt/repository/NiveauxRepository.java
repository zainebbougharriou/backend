package com.bezkoder.springjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.bezkoder.springjwt.models.Categorie;
import com.bezkoder.springjwt.models.Niveaux;

public interface NiveauxRepository extends CrudRepository<Niveaux, Integer> {

}
