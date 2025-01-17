package enicarthage.Projetweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import enicarthage.Projetweb.entity.Deadline;

import java.util.Date;
import java.util.List;

@Repository
public interface DeadlineRepository extends JpaRepository<Deadline, Long> {
    long count();
    List<Deadline> findByDateBetween(Date now, Date deuxJoursPlusTard);
}