package com.bezkoder.springjwt.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Niveaux;
import com.bezkoder.springjwt.repository.CategorieRepository;
import com.bezkoder.springjwt.repository.NiveauxRepository;

@Service
public class NiveauxService {

	@Autowired  
	NiveauxRepository nR; 
	
	@Autowired
	CategorieRepository cR; 

//getting all articles record by using the method findaAll() of CrudRepository  
public List<Niveaux> getAllNiveaux()   
{  
	List<Niveaux> Niveaux = new ArrayList<Niveaux>();  
	nR.findAll().forEach(r -> Niveaux.add(r));  
	return Niveaux;  	
}  

//getting a specific record by using the method findById() of CrudRepository  
public Niveaux getNiveauxById(int id)   
{  
	return nR.findById(id).get();  
}  


//saving a specific record by using the method save() of CrudRepository  
public Niveaux saveOrUpdate(Niveaux r)   
{  
	return nR.save(r);  
} 

//deleting a specific record by using the method deleteById() of CrudRepository  
public void delete(int id)   
{  
	nR.deleteById(id);  
} 

public List<Niveaux> getAllNiveauxByCategorie(int idCategorie) {
    List<Niveaux> Niveaux = new ArrayList<Niveaux>();
    nR.findAllByCategorie(cR.findById(idCategorie).get()).forEach(f -> Niveaux.add(f));
    return Niveaux;
}

}