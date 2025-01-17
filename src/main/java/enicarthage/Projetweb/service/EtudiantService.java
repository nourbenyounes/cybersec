package enicarthage.Projetweb.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.repository.EtudiantRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public Optional<Etudiant> authentifierEtudiant(String email, String password) {
        return etudiantRepository.findByEmailAndPassword(email, password);
    }

    public boolean existeEtudiantByEmail(String email) {
        return etudiantRepository.existsByEmail(email);
    }
    public Optional<Etudiant> findEtudiantById(String cin) {
        return etudiantRepository.findByCin(cin);
    }
    public Optional<Etudiant> findEtudiantByIdet(Etudiant etudiant) {
        return etudiantRepository.findByCin(etudiant.getCin());
    }
    public void modifierEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
    }

    
   
    
    

}