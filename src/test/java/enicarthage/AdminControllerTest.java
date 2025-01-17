package enicarthage;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import enicarthage.Projetweb.controller.AdminController;
import enicarthage.Projetweb.entity.Enseignant;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.repository.EnseignantRepository;
import enicarthage.Projetweb.repository.EtudiantRepository;

public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private EnseignantRepository enseignantRepository;
    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAjouterEnseignant() {
        // Arrange
        String nom = "Smith";
        String prenom = "Jane";
        String email = "jane.smith@example.com";
        String password = "password";
        String specialite = "Mathematiques";
        String cin = "87654321";
        
        Enseignant enseignant = new Enseignant();
        enseignant.setCin(cin);
        enseignant.setNom(nom);
        enseignant.setPrenom(prenom);
        enseignant.setEmail(email);
        enseignant.setMotDePasse(password);
        enseignant.setSpécialité(specialite);

        // Act
        String result = adminController.ajouterEnseignant(nom, prenom, email, password, specialite, cin);

        // Assert
        verify(enseignantRepository, times(1)).save(any(Enseignant.class));
        assertEquals("redirect:/admin/ajouter-etudiant", result);
    }
    @Test
    public void testAjouterEtudiant() {
        // Arrange
        String nom = "Doe";
        String prenom = "John";
        String email = "john.doe@example.com";
        String password = "password";
        String specialite = "Informatique";
        String cin = "12345678";
        
        Etudiant etudiant = new Etudiant();
        etudiant.setCin(cin);
        etudiant.setNom(nom);
        etudiant.setPrenom(prenom);
        etudiant.setEmail(email);
        etudiant.setMotDePasse(password);
        etudiant.setSpécialité(specialite);

        // Act
        String result = adminController.ajouterEtudiant(nom, prenom, email, password, specialite, cin);

        // Assert
        verify(etudiantRepository, times(1)).save(any(Etudiant.class));
        assertEquals("redirect:/admin/ajouter-etudiant", result);
    }
}
