package enicarthage.Projetweb.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.service.EtudiantService;
import jakarta.servlet.http.HttpSession;

@Controller
public class EtudiantController {


    @Autowired
    private EtudiantService etudiantService;

    @GetMapping("/modifier-etudiant")
    public String afficherFormulaireModificationEtudiant(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        //System.out.println(userId);
        Optional<Etudiant> etudiant = etudiantService.findEtudiantById(userId);

        if (etudiant.isPresent()) {
            model.addAttribute("etudiant", etudiant.get());
            return "modifEtudiant";
        } else {
            return "erreurAuthentification";
        }
    }

    @PostMapping("/modifier-etudiant")
    public String modifierEtudiant(@RequestParam("stage") String stage,@RequestParam("dateDebut") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam("dateFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin,
            @RequestParam("entreprise") String entreprise,@RequestParam("specialite") String specialite, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId);
        Optional<Etudiant> etudiantInDB = etudiantService.findEtudiantById(userId);
        System.out.println(etudiantInDB);

        if (etudiantInDB.isPresent()) {
            Etudiant existingEtudiant = etudiantInDB.get();
            existingEtudiant.setStage(stage);
            existingEtudiant.setDateDebut(dateDebut);
            existingEtudiant.setDateFin(dateFin);
            existingEtudiant.setEntreprise(entreprise);
            existingEtudiant.setSpecialite(specialite);
            System.out.println(existingEtudiant);

            // Save the modifications to the database
            etudiantService.modifierEtudiant(existingEtudiant);

            return "redirect:/pageEtudiant"; // Redirect to the student's home page
        } else {
            return "erreurAuthentification";
        }
    }

    @GetMapping("/pageEtudiant")
    public String showEtudiantPage(HttpSession session,Model model) {
        String userId = (String) session.getAttribute("userId");
        Optional<Etudiant> etudiantInDB = etudiantService.findEtudiantById(userId);
Etudiant etudiant=etudiantInDB.get();
        model.addAttribute("role", "etudiant");
        model.addAttribute("nomUtilisateur", etudiant.getEmail());
        return "pageEtudiant";
    }


}

