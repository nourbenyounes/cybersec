package enicarthage.Projetweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enicarthage.Projetweb.entity.Enseignant;

import java.util.Optional;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, String> {
    Optional<Enseignant> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}

