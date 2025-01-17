package enicarthage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineIntegrationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Indiquer le chemin vers EdgeDriver 
        System.setProperty("webdriver.edge.driver", "C:/Users/Nour2/Downloads/edgedriver_win64/msedgedriver.exe");

        // Initialiser EdgeDriver
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAddDeadline() throws InterruptedException {
        // Ouvrir l'application Web
        driver.get("http://localhost:8281/addDeadline");

        // Trouver les éléments du formulaire
        WebElement descriptionInput = driver.findElement(By.id("description"));
        WebElement dateInput = driver.findElement(By.id("date"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Remplir les champs du formulaire
        descriptionInput.sendKeys("Fin du projet");
        dateInput.sendKeys("31/12/2024");

        // Soumettre le formulaire
        submitButton.click();

        // Vérifier que la redirection est effectuée correctement
        Thread.sleep(2000); // Temps d'attente pour la redirection
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8281/deadline", currentUrl);

    }
    @Test
    public void testModifyDeadline() throws InterruptedException {
        // Ouvrir la page de modification d'une deadline (assurez-vous que l'ID 1 existe dans la base de données)
        driver.get("http://localhost:8281/deadline/7"); 

        // Trouver les éléments du formulaire de modification
        WebElement descriptionInput = driver.findElement(By.id("description"));
        WebElement dateInput = driver.findElement(By.id("date"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

        // Remplir les champs du formulaire avec de nouvelles valeurs
        descriptionInput.clear(); // Efface l'ancien texte
        descriptionInput.sendKeys("Nouvelle description de deadline");
        dateInput.clear(); // Efface la date précédente
        dateInput.sendKeys("31/12/2024"); // Nouvelle date

        // Soumettre le formulaire
        submitButton.click();

        // Vérifier que la redirection est effectuée correctement
        Thread.sleep(2000); // Temps d'attente pour la redirection, ajustez selon vos besoins
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8281/deadline", currentUrl);

    }
    @Test
    public void testDeleteDeadline() throws InterruptedException {
        // Ouvrir la page des deadlines
        driver.get("http://localhost:8281/deadlineadmin");

        // Trouver le bouton de suppression de la deadline 
        WebElement deleteButton = driver.findElement(By.xpath("//tr[td[text()='7']]//button[contains(text(), 'Supprimer')]")); // Remplacez '1' par l'ID de la deadline à supprimer

        // Cliquer sur le bouton de suppression
        deleteButton.click();

        // Vérifier que la redirection est effectuée correctement
        Thread.sleep(2000); 
        String currentUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:8281/deadline", currentUrl);

    }


    @AfterEach
    public void tearDown() {
        // Fermer le navigateur
        if (driver != null) {
            driver.quit();
        }
    }}
