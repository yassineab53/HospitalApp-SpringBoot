package net.yassine.hospitalapp.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.Date;

@Entity
/*@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder */
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@NotEmpty @Size(min = 4, max = 20)
    private String nom;
    //@Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private boolean malade;

    public Patient(Long id, String nom, Date dateNaissance, boolean malade, int score) {
        this.nom = nom;
        this.id = id;
        this.dateNaissance = dateNaissance;
        this.malade = malade;
        this.score = score;
    }



    public Patient() {
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", malade=" + malade +
                ", score=" + score +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setMalade(boolean malade) {
        this.malade = malade;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public boolean isMalade() {
        return malade;
    }

    public int getScore() {
        return score;
    }

    @Min(10)
    private int score;
}
