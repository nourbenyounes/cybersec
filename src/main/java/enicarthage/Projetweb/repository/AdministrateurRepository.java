package enicarthage.Projetweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enicarthage.Projetweb.entity.Administrateur;

import java.util.Optional;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, String> {
    Optional<Administrateur> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
}
