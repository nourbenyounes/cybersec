package enicarthage.Projetweb.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enicarthage.Projetweb.entity.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


}