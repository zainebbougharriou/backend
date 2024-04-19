package com.bezkoder.springjwt.repository;

import org.springframework.data.repository.CrudRepository;

import com.bezkoder.springjwt.models.Question;

public interface QuestionRepository  extends CrudRepository<Question, Integer> {

}
