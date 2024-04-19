package com.bezkoder.springjwt.controllers;
import com.bezkoder.springjwt.models.Proposition;
import com.bezkoder.springjwt.security.services.PropositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/proposition")
@CrossOrigin(origins = "*")

public class PropositionController {
    @Autowired
    PropositionService ps;
    //creating a get mapping that retrieves all the Article detail from the database
    @GetMapping("/Proposition")
    private List<Proposition> getAllProposition()
    {
        return ps.getAllProposition();
    }

    //creating a get mapping that retrieves the detail of a specific article
    @GetMapping("/Proposition/{id}")
    private Proposition getProposition(@PathVariable("id") int id)
    {
        return ps.getPropositionById(id);
    }

    //creating a delete mapping that deletes a specified article
    @DeleteMapping("/Proposition/{id}")
    private void deleteProposition(@PathVariable("id") int id)
    {
        ps.delete(id);
    }

    //create new article
    @PostMapping("/Proposition")
    private Proposition saveProposition(@RequestBody Proposition c)
    {

        return  ps.saveOrUpdate(c);
    }

    //creating put mapping that updates the article detail
    @PutMapping("/Proposition/{id}")
    private Proposition update(@PathVariable int id, @RequestBody Proposition c) {
        // Définissez l'identifiant de la catégorie avec la valeur de l'identifiant du chemin
        c.setIdProp(id);
        // Met à jour la catégorie existante
        return ps.saveOrUpdate(c);
    }

}
