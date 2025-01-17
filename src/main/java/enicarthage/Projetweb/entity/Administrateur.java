package enicarthage.Projetweb.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "administrateur")
public class Administrateur extends Personne {
    private String departement;
    
    public String getDépartement() {
        return departement;
    }

    public void setDépartement(String département) {
        this.departement = département;
    }
}
