package com.bezkoder.springjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.bezkoder.springjwt.models.Categorie;



public interface CategorieRepository extends CrudRepository<Categorie, Integer>{

    Categorie findByNomCategorie(String nomCategorie);

}
