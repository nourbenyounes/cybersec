package enicarthage.Projetweb.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import enicarthage.Projetweb.entity.Document;


public interface DocumentRepository extends JpaRepository<Document, Long> {

	List<Document> findByDestinataire(String string);

}

