package enicarthage.Projetweb.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "etudiant")
public class Etudiant extends Personne {
    private String stage;
    private Date dateDebut;
    private Date dateFin;
    private String entreprise;
    @OneToOne
    private Enseignant tuteurStage;
    private String specialite;
    

    public String getSpécialité() {
        return specialite;
    }

    public void setSpécialité(String spécialité) {
        this.specialite = spécialité;
    }




}