package enicarthage.Projetweb.service;


import java.util.Date;
import java.util.List;

import enicarthage.Projetweb.entity.Deadline;


public interface DeadlineService {
     Deadline createDeadline(String description, Date date);
    Deadline ajoutDeadline(Deadline deadline);
    void deleteDeadline(Long id);
    List<Deadline> getAllDeadlines();
    List<Deadline> getDeadlinesProches();
    Deadline getDeadline(Long id);
    void createDeadline(Deadline deadline);
     void modifierDeadline(Deadline deadline) ;

}