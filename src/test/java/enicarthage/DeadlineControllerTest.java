package enicarthage;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import enicarthage.Projetweb.controller.DeadlineController;
import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.service.DeadlineService;

public class DeadlineControllerTest {

    @InjectMocks
    private DeadlineController deadlineController;

    @Mock
    private DeadlineService deadlineService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddDeadline() {
        // Arrange
        String description = "Fin du projet";
        Date date = new Date(); // 
        // Act
        String result = deadlineController.addDeadline(description, date);

        // Assert
        verify(deadlineService, times(1)).createDeadline(description, date);
        assertEquals("redirect:/deadline", result);
    }
}
