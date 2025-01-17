package enicarthage.Projetweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Personne;
import enicarthage.Projetweb.repository.PersonneRepository;

import java.util.Optional;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public Optional<Personne> authentifier(String email, String password) {
        return personneRepository.findByEmailAndPassword(email, password);
    }

    
    
    public boolean existeByEmail(String email) {
        return personneRepository.existsByEmail(email);
    }
}
