package enicarthage.Projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import enicarthage.Projetweb.entity.Administrateur;
import enicarthage.Projetweb.entity.Document;
import enicarthage.Projetweb.entity.Enseignant;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.service.AdministrateurService;
import enicarthage.Projetweb.service.DocumentService;
import enicarthage.Projetweb.service.EmailService;
import enicarthage.Projetweb.service.EnseignantService;
import enicarthage.Projetweb.service.EtudiantService;
import enicarthage.Projetweb.service.JwtUtil;
import enicarthage.Projetweb.service.PersonneService;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*@Controller
public class AuthentificationController {

    @Autowired
    private PersonneService personneService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/log")
    public String authentifier(@RequestParam Map<String, String> credentials, HttpSession session) {
        String email = credentials.get("email");
        String motDePasse = credentials.get("password");
        System.out.println(email+motDePasse);

        //Optional<Personne> personne = personneService.authentifier(email, motDePasse);
        Optional<Etudiant> etudiant = etudiantService.authentifierEtudiant(email, motDePasse);
        Optional<Enseignant> enseignant = enseignantService.authentifierEnseignant(email, motDePasse);
        Optional<Administrateur> administrateur = administrateurService.authentifierAdministrateur(email, motDePasse);

      /*if (personne.isPresent()) {

            return "pagePersonne";
        } else if (etudiant.isPresent()) {
            session.setAttribute("utilisateurConnecte", etudiant.get());
            
            
            

            return "pageEtudiant";
        } else if (enseignant.isPresent()) {
            System.out.println("yes");
            session.setAttribute("utilisateurConnecte", enseignant.get());

            return "pageEnseignant";
        } else if (administrateur.isPresent()) {
            session.setAttribute("utilisateurConnecte", administrateur.get());

            return "pageAdministrateur";
        } else {
            return "erreurAuthentification";
        }
    }  
}*/


@Controller
public class AuthentificationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PersonneService personneService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private AdministrateurService administrateurService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DocumentService documentService;
    //@Autowired
   // private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/log")
    public String authentifier(@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session, Model model) {
        Optional<Etudiant> etudiant = etudiantService.authentifierEtudiant(email, password);
        Optional<Enseignant> enseignant = enseignantService.authentifierEnseignant(email, password);
        Optional<Administrateur> administrateur = administrateurService.authentifierAdministrateur(email, password);
        if (etudiant.isPresent()) {
            session.setAttribute("userId", etudiant.get().getCin());

            String token = jwtUtil.generateToken(etudiant.get(), "etudiant");
            emailService.sendTokenByEmail(email, token);
            model.addAttribute("role", "etudiant");
            model.addAttribute("nomUtilisateur", etudiant.get().getNom());
            return "redirect:/welcome";
        } else if (enseignant.isPresent()) {
            session.setAttribute("userId", enseignant.get().getCin());

            String token = jwtUtil.generateToken(enseignant.get(), "enseignant");
            emailService.sendTokenByEmail(email, token);
            model.addAttribute("role", "enseignant");
            model.addAttribute("nomUtilisateur", enseignant.get().getNom());
            return "redirect:/welcome";
        } else if (administrateur.isPresent()) {
            session.setAttribute("userId", administrateur.get().getCin());

            String token = jwtUtil.generateToken(administrateur.get(), "administrateur");
            emailService.sendTokenByEmail(email, token);
            model.addAttribute("role", "administrateur");
            model.addAttribute("nomUtilisateur", administrateur.get().getNom());
            return "redirect:/welcome";
        } else {
            return "redirect:/erreurAuthentification";
        }
    }

    @PostMapping("/access-page")
    public String accessPage(@RequestParam("token") String token, Model model) {
        String role = jwtUtil.extractRole(token);
        String nom = jwtUtil.extractNom(token);

        if ("etudiant".equals(role)) {
            model.addAttribute("role", role);
            model.addAttribute("nomUtilisateur", nom);
            return "pageEtudiant";
        } else if ("enseignant".equals(role)) {
            model.addAttribute("role", role);
            model.addAttribute("nomUtilisateur", nom);
            return "pageEnseignant";
        } else if ("administrateur".equals(role)) {
            model.addAttribute("role", role);
            model.addAttribute("nomUtilisateur", nom);
        
                List<Document> documents = documentService.getDocumentsForAdministrateur();
                System.out.println(documents);
                System.out.println("yes");
                model.addAttribute("documents", documents);

            
            return "pageAdministrateur";
        } else {
            return "erreurAuthentification";
        }
    }
}
