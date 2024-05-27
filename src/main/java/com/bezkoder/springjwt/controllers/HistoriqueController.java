package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.SpringBootSecurityJwtApplication;
import com.bezkoder.springjwt.controllers.dto.HistoryDTO;
import com.bezkoder.springjwt.controllers.dto.HistoryRequestDTO;
import com.bezkoder.springjwt.models.Quiz;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bezkoder.springjwt.models.Historique;
import com.bezkoder.springjwt.security.services.HistoriqueService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historique")
@CrossOrigin(origins = "*")
public class HistoriqueController {

    @Autowired
    private HistoriqueService historiqueService;

    @Autowired
    private QuizService quizService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public List<HistoryDTO> getAllHistorique() {
        List<Historique> historiqueList = historiqueService.getAllHistorique();

        User user = userRepository.findByUsername(SpringBootSecurityJwtApplication.getCurrentUser().getUsername()).get();

        if (user != null && user.getRoles().contains(new Role(Role.ERole.ROLE_DEVELOPPEUR))) {
            return historiqueList.stream()
                    .filter(h -> Objects.equals(h.getUtilisateur().getId(), user.getId()))
                    .map(HistoryDTO::map)
                    .collect(Collectors.toList());
        }

        return historiqueList.stream().map(HistoryDTO::map).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Historique getHistorique(@PathVariable int id) {
        return historiqueService.getHistoriqueById(id);
    }

    @PostMapping("/add")
    public Historique saveHistorique(@RequestBody HistoryRequestDTO historyRequestDTO) {

        if(historyRequestDTO == null ){
            return null ;
        }

        Quiz quiz = quizService.getQuizById(historyRequestDTO.getIdQuiz())  ;
        User user = userRepository.findByUsername(SpringBootSecurityJwtApplication.getCurrentUser().getUsername()).get();
        if(quiz == null || user==null){
            return null ;
        }
        Historique historique = new Historique();
            historique.setDateHeure(new Date());
            historique.setQuiz(quiz);
            historique.setUtilisateur(user);
            historique.setScore(historyRequestDTO.getScore());

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
        String formattedScore = historique.getScore();
        String formattedTemps = historique.getFormattedTemps();

        // Construire un objet pour la réponse qui contient les informations formatées
        Map<String, String> details = new HashMap<>();
        details.put("dateHeure", formattedDateHeure);
        details.put("score", formattedScore);
        details.put("temps", formattedTemps);

        return ResponseEntity.ok(details);
    }
}
