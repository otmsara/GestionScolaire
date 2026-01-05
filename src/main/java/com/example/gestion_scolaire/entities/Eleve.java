package com.example.gestion_scolaire.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
public class Eleve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;


    @ManyToOne
    private Filiere filiere;


    @ManyToMany
    private List<Cours> cours;


    @OneToOne(mappedBy = "eleve", cascade = CascadeType.ALL)
    private DossierAdministratif dossierAdministratif;



    public DossierAdministratif getDossierAdministratif() {
        return dossierAdministratif;
    }

    public void setDossierAdministratif(DossierAdministratif dossierAdministratif) {
        this.dossierAdministratif = dossierAdministratif;
    }

}
