package enicarthage.Projetweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class pagePersonneController {

    @GetMapping("/pagePersonne")
    public static String afficherPagePersonne() {
        return "pagePersonne"; 
    }
}

