package enicarthage.Projetweb.service;


	import java.util.List;

import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.entity.Notification;


	public interface NotificationService {
	    Notification createNotification(String destination, Deadline deadline);
	    void addNotification(Notification notification);
	    void deleteNotification(Long id);
	    List<Notification> getAllNotifications();
	    void generateNotificationsForDeadlines();
	}