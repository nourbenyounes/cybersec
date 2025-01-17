package enicarthage.Projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import enicarthage.Projetweb.entity.Document;
import enicarthage.Projetweb.entity.Enseignant;
import enicarthage.Projetweb.service.DocumentService;
import enicarthage.Projetweb.service.EtudiantService;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private EtudiantService etudiantService;
    @GetMapping("/upload")
    public String uploadDocumentForm() {
    	
        return "upload"; 
    }

    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("titre") String titre, @RequestParam("file") MultipartFile file,@RequestParam("destinataire") String destinataire,HttpSession session) {
        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
                String chemin = file.getOriginalFilename();
                String source = (String) session.getAttribute("userId");

                documentService.saveDocument(titre, chemin, fileContent,destinataire,source);
                System.out.println(source);
                //return "redirect:/welcome";
            } catch (IOException e) {
                //return "redirect:/erreurAuthentification";
                System.out.println("erreur");

            }
        } else {
            //return "redirect:/erreurAuthentification";
            System.out.println("erreur");

        }
    }
    @GetMapping("/liste-document")
    public String listeressources(Model model) {
    	List<Document> documents = documentService.getDocumentsForEtudiant();

        model.addAttribute("documents", documents);
        return"/liste-document";}
    
    
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable("id") Long id) {
        Optional<Document> optionalDocument = documentService.getDocumentById(id);
        
        if (!optionalDocument.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Document document = optionalDocument.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", document.getChemin());

        return new ResponseEntity<>(document.getfileContent(), headers, HttpStatus.OK);
    }
    @GetMapping("/ajouter-document")
    public String afficherFormulaireAjoutEnseignant(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "upload";
    }
    

    
    @GetMapping("/ajouter-document-etudiant")
    public String uploadDocumentFormEtudiant() {
        return "ajouter-document-etudiant"; 
    }

    @PostMapping("/ajouter-document-etudiant")
    public void handleFileUploadEtudiant(@RequestParam("titre") String titre, @RequestParam("file") MultipartFile file,@RequestParam("destinataire") String destinataire,HttpSession session) {
        if (!file.isEmpty()) {
            try {
                byte[] fileContent = file.getBytes();
                String chemin = file.getOriginalFilename();
                String source = (String) session.getAttribute("userId");

                documentService.saveDocument(titre, chemin, fileContent,destinataire,source);

                System.out.println(source);
                //return "redirect:/welcome";
            } catch (IOException e) {
                //return "redirect:/erreurAuthentification";
                System.out.println("erreur");

            }
        } else {
            //return "redirect:/erreurAuthentification";
            System.out.println("erreur");

        }
    }
    
    

}
