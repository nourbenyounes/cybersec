package enicarthage.Projetweb.service;


import java.util.Date;
import java.util.List;
import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import enicarthage.Projetweb.entity.Notification;

@Service
public class NotificationServiceImpl implements NotificationService {


    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private DeadlineService deadlineService;

    @Override
    public Notification createNotification(String destination, Deadline deadline) {
        String notificationMessage = deadline.getDescription() + " - Deadline dans 2 jours.";
        return new Notification(notificationMessage, destination, new Date());
    }
    @Override
    public void addNotification(Notification notification) {
        notificationRepository.save(notification);
    }
    @Override
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new NotificationNotFoundException("La notification avec l'ID " + id + " n'existe pas.");
        }
        notificationRepository.deleteById(id);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public void generateNotificationsForDeadlines() {
        List<Deadline> deadlines = deadlineService.getDeadlinesProches();
        for (Deadline deadline : deadlines) {
            Notification notification = createNotification("Destinataire", deadline);
            addNotification(notification);
        }
    }
}