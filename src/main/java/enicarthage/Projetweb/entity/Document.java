package enicarthage.Projetweb.entity;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode

@Table
public class Document implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "id")
    
    private Long id;

    @Column
    private String titre;


    @Column
    private String chemin;
    @Column
    private String source;
    @Column
    private String destinataire;

    @Column
    private byte[] taille ;
    
    @Lob
    @Column(name = "filecontent", columnDefinition="LONGBLOB")

    private byte[] fileContent;
    
public String getChemin() {return this.chemin;}
public byte[] getfileContent() {return this.fileContent;}


}