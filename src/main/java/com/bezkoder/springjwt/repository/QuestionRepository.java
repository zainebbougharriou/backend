package com.bezkoder.springjwt.repository;

import com.bezkoder.springjwt.models.Niveaux;
import com.bezkoder.springjwt.models.Quiz;
import org.springframework.data.repository.CrudRepository;

import com.bezkoder.springjwt.models.Question;

public interface QuestionRepository  extends CrudRepository<Question, Integer> {
    public java.lang.Iterable<Question> findAllByQuiz(Quiz R);

}
