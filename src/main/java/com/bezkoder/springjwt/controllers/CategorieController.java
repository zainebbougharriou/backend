package com.bezkoder.springjwt.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Categorie;
import com.bezkoder.springjwt.security.services.CategorieService;



//mark class as Controller  
@RestController
@RequestMapping("/cat")
@CrossOrigin(origins = "*")

public class CategorieController {
	//autowire the ArticleService class  
	@Autowired  
	CategorieService cs; 
	//creating a get mapping that retrieves all the Article detail from the database   
	@GetMapping("/Categorie")
	private List<Categorie> getAllCategories()   
	{  
		return cs.getAllCategories();  
	}  

	//creating a get mapping that retrieves the detail of a specific article  
	@GetMapping("/Categorie/{id}")  
	private Categorie getCategorie(@PathVariable("id") int id)   
	{  
		return cs.getCategoriesById(id);  
	}  

	//creating a delete mapping that deletes a specified article  
	@DeleteMapping("/Categorie/{id}")  
	private void deleteCategorie(@PathVariable("id") int id)   
	{  
		cs.delete(id);  
	} 

	//create new article
	@PostMapping("/Categorie")  
	private Categorie saveCategorie(@RequestBody Categorie c)   
	{  
		  
		return  cs.saveOrUpdate(c);
	} 

	//creating put mapping that updates the article detail
	@PutMapping("/Categorie/{id}")
	private Categorie update(@PathVariable int id, @RequestBody Categorie c) {
	    // Définissez l'identifiant de la catégorie avec la valeur de l'identifiant du chemin
	    c.setIdCategorie(id);
	    // Met à jour la catégorie existante
	    return cs.saveOrUpdate(c);
	}

}