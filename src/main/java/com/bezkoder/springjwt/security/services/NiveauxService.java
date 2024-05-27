package com.bezkoder.springjwt.security.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.springjwt.models.Niveaux;
import com.bezkoder.springjwt.repository.NiveauxRepository;

@Service
public class NiveauxService {

	@Autowired  
	NiveauxRepository nR; 
	


//getting all articles record by using the method findaAll() of CrudRepository  
public List<Niveaux> getAllNiveaux() {
	List<Niveaux> niveauxList = new ArrayList<>();
	nR.findAll().forEach(niveauxList::add);
	niveauxList.sort(Comparator.comparingInt(Niveaux::getPriorityNiveau));
	return niveauxList;
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



}