package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.springjwt.models.Historique;

import java.util.List;

public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {
    List<Historique> findByQuizIdQuiz(int quizId);

}
