package com.bezkoder.springjwt.security.services;

import com.bezkoder.springjwt.models.Proposition;
import com.bezkoder.springjwt.repository.PropositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PropositionService {
    @Autowired
    PropositionRepository pR;


    //getting all articles record by using the method findaAll() of CrudRepository
    public List<Proposition> getAllProposition()
    {
        List<Proposition> Proposition = new ArrayList<Proposition>();
        pR.findAll().forEach(r -> Proposition.add(r));
        return Proposition;
    }

    //getting a specific record by using the method findById() of CrudRepository
    public Proposition getPropositionById(int id)
    {
        return pR.findById(id).get();
    }


    //saving a specific record by using the method save() of CrudRepository
    public Proposition saveOrUpdate(Proposition r)
    {
        return pR.save(r);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        pR.deleteById(id);
    }

}
