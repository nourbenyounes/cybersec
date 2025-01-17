package enicarthage.Projetweb.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "enseignant")
public class Enseignant extends Personne {
    private String specialite;
    
    public String getSpécialité() {
        return specialite;
    }

    public void setSpécialité(String spécialité) {
        this.specialite = spécialité;
    }
}

