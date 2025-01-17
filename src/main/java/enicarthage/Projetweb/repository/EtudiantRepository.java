package enicarthage.Projetweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enicarthage.Projetweb.entity.Etudiant;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, String> {
    Optional<Etudiant> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    Optional<Etudiant> findEtudiantByCin(String cin) ;
	Optional<Etudiant> findByCin(String cin);



}
