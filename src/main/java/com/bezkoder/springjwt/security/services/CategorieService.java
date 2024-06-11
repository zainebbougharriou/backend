package com.bezkoder.springjwt.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Categorie;
import com.bezkoder.springjwt.repository.CategorieRepository;


@Service 
public class CategorieService {
	//defining the business logic  

@Autowired  
CategorieRepository cR; 

//getting all articles record by using the method findaAll() of CrudRepository  
public List<Categorie> getAllCategories()   
{  
	List<Categorie> Categorie = new ArrayList<Categorie>();  
	cR.findAll().forEach(r -> Categorie.add(r));  
	return Categorie;  	
}  

//getting a specific record by using the method findById() of CrudRepository  
public Categorie getCategoriesById(int id)   
{  
	return cR.findById(id).get();  
}

public Categorie findCategorieByNom(String nomCategorie) {
	return cR.findByNomCategorie(nomCategorie);
}

//saving a specific record by using the method save() of CrudRepository  
public Categorie saveOrUpdate(Categorie r)   
{  
	return cR.save(r);  
} 

//deleting a specific record by using the method deleteById() of CrudRepository  
public void delete(int id)   
{  
	cR.deleteById(id);  
} 
}