package enicarthage.Projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.service.DeadlineService;
import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Controller
public class DeadlineController {

    @Autowired
    private DeadlineService deadlineService;

    // Affiche toutes les deadlines
    @GetMapping("/deadline")
    public String showDeadlines(Model model) {
        List<Deadline> deadlines = deadlineService.getAllDeadlines();
        model.addAttribute("deadlines", deadlines);
        return "deadline"; // Retourne la vue "deadline.html"
    }
    // Affiche toutes les deadlines
    @GetMapping("/deadlineadmin")
    public String showDeadlinesadmin(Model model) {
        List<Deadline> deadlines = deadlineService.getAllDeadlines();
        model.addAttribute("deadlines", deadlines);
        return "deadlineadmin"; // Retourne la vue "deadline.html"
    }


    // Affiche le formulaire pour ajouter une deadline
    @GetMapping("/addDeadline")
    public String addDeadlines(Model model) {
        return "formulaire-ajout-deadline"; // Retourne la vue "formulaire-ajout-deadline.html"
    }

    // Traite la soumission du formulaire d'ajout de deadline
    @PostMapping("/addDeadline")
    public String addDeadline(@RequestParam String description, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        deadlineService.createDeadline(description, date);
        return "redirect:/deadline"; // Redirige vers la page d'affichage des deadlines
    }

    // Affiche le formulaire pour ajouter une deadline (version avec ModelAttribute)
    @GetMapping("/addDeadlineForm")
    public String showAddDeadlineForm(Model model) {
        model.addAttribute("deadline", new Deadline());
        return "deadlineForm"; // Retourne la vue "deadlineForm.html"
    }

   // Affiche le formulaire de modification d'une deadline spécifique
@GetMapping("/deadline/{id}")
public String showmodifDeadlineForm(Model model, @PathVariable Long id, HttpSession session) {
    Deadline deadline = deadlineService.getDeadline(id);
    model.addAttribute("deadline", deadline);
    session.setAttribute("ddlneId", deadline.getId());

    return "modifierDeadlineForm"; // Retourne la vue "modifierDeadlineForm.html"
}

@PostMapping("/deadline/{id}")
public String modifierDeadline(@RequestParam("description") String description,@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
         HttpSession session) {
	long ddlneId =  (long) session.getAttribute("ddlneId");
    Deadline ddlneInDB = deadlineService.getDeadline(ddlneId);
    ddlneInDB.setDate(date);
    ddlneInDB.setDescription(description);


        // Save the modifications to the database
    deadlineService.modifierDeadline(ddlneInDB);
        return "redirect:/deadline"; // Redirect to the student's home page
    
}

    // Supprime une deadline spécifique
    @PostMapping("/deadline/{id}/delete")
    public String deleteDeadline(@PathVariable Long id) {
        deadlineService.deleteDeadline(id);
        return "redirect:/deadline"; // Redirige vers la page d'affichage des deadlines
    }
}