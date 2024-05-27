package com.bezkoder.springjwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bezkoder.springjwt.models.Historique;
import com.bezkoder.springjwt.models.Question;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.HistoriqueRepository;
import com.bezkoder.springjwt.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HistoriqueService {

    @Autowired  
    private HistoriqueRepository historiqueRepository;

    @Autowired  
    private UserRepository userRepository;

    public List<Historique> getAllHistorique() {
        return historiqueRepository.findAll();
    }

    public Historique getHistoriqueById(int id) {
        return historiqueRepository.findById(id).orElse(null);
    }

    public Historique saveOrUpdate(Historique historique) {
        // Vérifier que l'utilisateur existe
        User utilisateur = userRepository.findById(historique.getUtilisateur().getId())
                              .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
        historique.setUtilisateur(utilisateur);

        // Sauvegarder l'historique
        return historiqueRepository.save(historique);
    }

    public void delete(int id) {
        historiqueRepository.deleteById(id);
    }

    // Méthode pour calculer le score
    public int calculateScore(List<Question> questions, List<String> givenAnswers) {
        int correctAnswers = 0;
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String correctAnswer = question.getReponseCorrect();
            String givenAnswer = givenAnswers.get(i);
            if (correctAnswer.equals(givenAnswer)) {
                correctAnswers++;
            }
        }
        return (int) ((double) correctAnswers / questions.size() * 100);
    }

    public void deleteHistoriqueByQuizId(int idQuiz) {
        List<Historique>  historiqueList = historiqueRepository.findByQuizIdQuiz(idQuiz);
        historiqueList.forEach(h-> {
            h.setUtilisateur(null);
            historiqueRepository.deleteById(h.getIdHistorique());
        });
    }
}
