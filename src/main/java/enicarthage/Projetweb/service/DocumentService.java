package enicarthage.Projetweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enicarthage.Projetweb.entity.Document;
import enicarthage.Projetweb.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public void saveDocument(String titre, String chemin, byte[] fileContent,String destinataire, String source) {
        Document document = new Document();
        document.setTitre(titre);
        document.setChemin(chemin);
        int taille=(fileContent.length);
        document.setDestinataire(destinataire);
        document.setSource(source);



        document.setFileContent(fileContent);
        document.setId((long)20 );
        documentRepository.save(document);
    }
    public List<Document> getDocumentsForAdministrateur() {
        return documentRepository.findByDestinataire("administrateur");
    }
	public Optional<Document> getDocumentById(Long id) {
		return documentRepository.findById(id);
	}
	public List<Document> getDocumentsForEtudiant() {
        return documentRepository.findByDestinataire("etudiant");

	}
}
