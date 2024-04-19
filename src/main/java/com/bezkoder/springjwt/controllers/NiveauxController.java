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

import com.bezkoder.springjwt.models.Niveaux;
import com.bezkoder.springjwt.security.services.NiveauxService;
@RestController
@RequestMapping("/Niveau")
@CrossOrigin(origins = "*")
public class NiveauxController {
	//autowire the ArticleService class  
		@Autowired  
		NiveauxService ns; 
		//creating a get mapping that retrieves all the Article detail from the database   
		@GetMapping("/Niveaux")
		private List<Niveaux> getAllNiveaux()   
		{  
			return ns.getAllNiveaux();  
		}  

		//creating a get mapping that retrieves the detail of a specific article  
		@GetMapping("/Niveaux/{id}")  
		private Niveaux getNiveaux(@PathVariable("id") int id)   
		{  
			return ns.getNiveauxById(id);  
		}  

		//creating a delete mapping that deletes a specified article  
		@DeleteMapping("/Niveaux/{id}")  
		private void deleteNiveaux(@PathVariable("id") int id)   
		{  
			ns.delete(id);  
		} 

		//create new article
		@PostMapping("/Niveaux")  
		private Niveaux saveNiveaux(@RequestBody Niveaux c)   
		{  
			  
			return  ns.saveOrUpdate(c);
		} 

		//creating put mapping that updates the article detail
		@PutMapping("/Niveaux/{id}")
		private Niveaux update(@PathVariable int id, @RequestBody Niveaux c) {
		    // Définissez l'identifiant de la catégorie avec la valeur de l'identifiant du chemin
		    c.setIdNiveau(id);
		    // Met à jour la catégorie existante
		    return ns.saveOrUpdate(c);
		}
		@GetMapping("/NiveauxByIdCategorie/{idCategorie}")
		private List<Niveaux> getAllNiveauxByIdCategorie(@PathVariable("idCategorie") int idCategorie)   
		{  
			return ns.getAllNiveauxByCategorie(idCategorie);  
		}  
	}