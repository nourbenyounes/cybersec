package enicarthage.Projetweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Enseignant;
import enicarthage.Projetweb.repository.EnseignantRepository;

import java.util.Optional;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    public Optional<Enseignant> authentifierEnseignant(String email, String password) {
        return enseignantRepository.findByEmailAndPassword(email, password);
    }

    public boolean existeEnseignantByEmail(String email) {
        return enseignantRepository.existsByEmail(email);
    }
}
