package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Proposition;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropositionRepository extends CrudRepository<Proposition, Integer> {
    List<Proposition> findByQuestionIdQuestion(int idQuestion);
}
