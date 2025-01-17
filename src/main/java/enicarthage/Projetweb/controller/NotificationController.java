package enicarthage.Projetweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.entity.Notification;
import enicarthage.Projetweb.service.DeadlineService;
import enicarthage.Projetweb.service.NotificationService;

import java.util.Date;
import java.util.List;
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public String shownotifications(Model model) {
       notificationService.generateNotificationsForDeadlines();
       List<Notification> notifs=notificationService.getAllNotifications();
        model.addAttribute("notifs", notifs);
        return "notifications"; // Retourne la vue "deadline.html"
    }
    @PostMapping("/notifications/{id}/delete")
    public String deleteDeadline(@PathVariable Long id) {
    	notificationService.deleteNotification(id);
        return "redirect:/notifications"; // Redirige vers la page d'affichage des deadlines
    }
   

 
}