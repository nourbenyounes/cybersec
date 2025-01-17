package enicarthage.Projetweb.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import enicarthage.Projetweb.entity.Enseignant;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.repository.EnseignantRepository;
import enicarthage.Projetweb.repository.EtudiantRepository;
import enicarthage.Projetweb.service.DocumentService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Autowired
    private DocumentService documentService;
    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;
    @GetMapping("/ajouter-etudiant")
    public String afficherFormulaireAjoutEtudiant(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "formulaire-ajout-etudiant";
    }

    @PostMapping("/ajouter-etudiant")
    public String ajouterEtudiant(@RequestParam("nom") String nom,
                                  @RequestParam("prenom") String prenom,
                                  @RequestParam("email") String email,
                                  @RequestParam("password") String password,
                                  @RequestParam("specialite") String specialite,

    @RequestParam("cin") String cin)
{       // String motDePasseHash = passwordEncoder.encode(password);

        Etudiant etudiant = new Etudiant();
        etudiant.setCin(cin);

        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setMotDePasse(password);
        etudiant.setSpécialité(specialite);


        etudiantRepository.save(etudiant);

        return "redirect:/admin/ajouter-etudiant";
    }
    @GetMapping("/ajouter-enseignant")
    public String afficherFormulaireAjoutEnseignant(Model model) {
        model.addAttribute("enseignant", new Enseignant());
        return "formulaire-ajout-enseignant";
    }

    @PostMapping("/ajouter-enseignant")
    public String ajouterEnseignant(@RequestParam("nom") String nom,
                                    @RequestParam("prenom") String prenom,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    @RequestParam("specialite") String specialite,
                                    @RequestParam("cin") String cin) {
        // String motDePasseHash = passwordEncoder.encode(password);

        Enseignant enseignant = new Enseignant();
        enseignant.setCin(cin);

        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setEmail(email);
        enseignant.setMotDePasse(password);
        enseignant.setSpécialité(specialite);

        enseignantRepository.save(enseignant);

        return "redirect:/admin/ajouter-etudiant";
    }



}
