package com.bezkoder.springjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bezkoder.springjwt.models.Historique;
import com.bezkoder.springjwt.security.services.HistoriqueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/historique")
@CrossOrigin(origins = "*")
public class HistoriqueController {

    @Autowired  
    private HistoriqueService historiqueService;

    @GetMapping
    public List<Historique> getAllHistorique() {
        return historiqueService.getAllHistorique();
    }

    @GetMapping("/{id}")
    public Historique getHistorique(@PathVariable int id) {
        return historiqueService.getHistoriqueById(id);
    }

    @PostMapping
    public Historique saveHistorique(@RequestBody Historique historique) {
        return historiqueService.saveOrUpdate(historique);
    }

    @PutMapping("/{id}")
    public Historique updateHistorique(@PathVariable int id, @RequestBody Historique historique) {
        historique.setIdHistorique(id);
        return historiqueService.saveOrUpdate(historique);
    }

    @DeleteMapping("/{id}")
    public void deleteHistorique(@PathVariable int id) {
        historiqueService.delete(id);
    }
    
    @GetMapping("/{id}/details")
    public ResponseEntity<?> getHistoriqueDetails(@PathVariable int id) {
        Historique historique = historiqueService.getHistoriqueById(id);
        if (historique == null) {
            return ResponseEntity.notFound().build();
        }

        String formattedDateHeure = historique.getFormattedDateHeure();
        String formattedScore = historique.getFormattedScore();
        String formattedTemps = historique.getFormattedTemps();

        // Construire un objet pour la réponse qui contient les informations formatées
        Map<String, String> details = new HashMap<>();
        details.put("dateHeure", formattedDateHeure);
        details.put("score", formattedScore);
        details.put("temps", formattedTemps);

        return ResponseEntity.ok(details);
    }
}
