package enicarthage.Projetweb.service;


import enicarthage.Projetweb.entity.Deadline;
import enicarthage.Projetweb.entity.Etudiant;
import enicarthage.Projetweb.repository.DeadlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class DeadlineServiceImpl implements DeadlineService {

    @Autowired
    private DeadlineRepository deadlineRepository;

    @Override
    public Deadline createDeadline(String description, Date date) {
        Deadline deadline = new Deadline(description, date);
        return deadlineRepository.save(deadline);
    }

    @Override
    public Deadline ajoutDeadline(Deadline deadline) {
        return deadlineRepository.save(deadline);
    }

    @Override
    public void modifierDeadline(Deadline deadline) {
        deadlineRepository.save(deadline);
    }

    @Override
    public void deleteDeadline(Long id) {
        Optional<Deadline> existingDeadline = deadlineRepository.findById(id);
        if (existingDeadline.isPresent()) {
            deadlineRepository.deleteById(id);
        } else {
            throw new DeadlineNotFoundException("La deadline avec l'ID " + id + " n'existe pas.");
        }
    }

    @Override
    public List<Deadline> getAllDeadlines() {
        return deadlineRepository.findAll();
    }

    @Override
    public List<Deadline> getDeadlinesProches() {
        Date now = new Date();
        Date deuxJoursPlusTard = new Date(now.getTime() + 2 * 24 * 60 * 60 * 1000); // Ajoute 2 jours en millisecondes
        return deadlineRepository.findByDateBetween(now, deuxJoursPlusTard);
    }
    @Override
    public Deadline getDeadline(Long id) {
        return deadlineRepository.findById(id)
                .orElseThrow(() -> new DeadlineNotFoundException("La deadline avec l'ID " + id + " n'existe pas."));
    }
    @Override
    public void createDeadline(Deadline deadline) {
        deadlineRepository.save(deadline);
    }

}