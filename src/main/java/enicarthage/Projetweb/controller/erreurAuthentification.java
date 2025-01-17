package enicarthage.Projetweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class erreurAuthentification {

    @GetMapping("/erreurAuthentification")
    public String erreurAuth() {
        return "erreurAuthentification"; 
    }
}

