package enicarthage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import enicarthage.Projetweb.controller.AuthentificationController;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.service.AdministrateurService;
import enicarthage.Projetweb.service.EmailService;
import enicarthage.Projetweb.service.EnseignantService;
import enicarthage.Projetweb.service.EtudiantService;
import enicarthage.Projetweb.service.JwtUtil;

import jakarta.servlet.http.HttpSession;

public class AuthentificationControllerTest {

    @InjectMocks
    private AuthentificationController authentificationController;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private EtudiantService etudiantService;

    @Mock
    private EnseignantService enseignantService;

    @Mock
    private AdministrateurService administrateurService;

    @Mock
    private EmailService emailService;

    @Mock
    private HttpSession session;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthentifierEtudiant() {
        // Arrange: Simuler un étudiant authentifié
        Etudiant etudiant = new Etudiant();
        etudiant.setCin("12345");
        etudiant.setNom("John");

        when(etudiantService.authentifierEtudiant("student@example.com", "password"))
            .thenReturn(Optional.of(etudiant));
        when(jwtUtil.generateToken(etudiant, "etudiant")).thenReturn("token123");

        // Act: Appeler la méthode authentifier
        String result = authentificationController.authentifier("student@example.com", "password", session, model);

        // Assert: Vérifier le résultat
        assertEquals("redirect:/welcome", result);
        verify(session).setAttribute("userId", "12345");
        verify(emailService).sendTokenByEmail("student@example.com", "token123");
        verify(model).addAttribute("role", "etudiant");
        verify(model).addAttribute("nomUtilisateur", "John");
    }

    @Test
    public void testAuthentifierAucunUtilisateur() {
        // Arrange: Aucun utilisateur authentifié
        when(etudiantService.authentifierEtudiant("invalid@example.com", "wrongpassword"))
            .thenReturn(Optional.empty());
        when(enseignantService.authentifierEnseignant("invalid@example.com", "wrongpassword"))
            .thenReturn(Optional.empty());
        when(administrateurService.authentifierAdministrateur("invalid@example.com", "wrongpassword"))
            .thenReturn(Optional.empty());

        // Act: Appeler la méthode authentifier avec des informations incorrectes
        String result = authentificationController.authentifier("invalid@example.com", "wrongpassword", session, model);

        // Assert: Redirection vers la page d'erreur
        assertEquals("redirect:/erreurAuthentification", result);
    }
    @Test
    public void testAccessPageEtudiant() {
        // Arrange: Simuler un token valide pour un étudiant
        when(jwtUtil.extractRole("token123")).thenReturn("etudiant");
        when(jwtUtil.extractNom("token123")).thenReturn("John");

        // Act: Appeler la méthode accessPage
        String result = authentificationController.accessPage("token123", model);

        // Assert: Vérifier la redirection et les attributs du modèle
        assertEquals("pageEtudiant", result);
        verify(model).addAttribute("role", "etudiant");
        verify(model).addAttribute("nomUtilisateur", "John");
    }

}
