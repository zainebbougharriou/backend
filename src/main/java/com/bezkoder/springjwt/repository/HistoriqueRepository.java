package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bezkoder.springjwt.models.Historique;

public interface HistoriqueRepository extends JpaRepository<Historique, Integer> {
}
