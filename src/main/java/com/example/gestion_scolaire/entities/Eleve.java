package com.example.gestion_scolaire.entities;

import jakarta.persistence.*;
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

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public List<Cours> getCours() {
        return cours;
    }

    public void setCours(List<Cours> cours) {
        this.cours = cours;
    }

    public DossierAdministratif getDossierAdministratif() {
        return dossierAdministratif;
    }

    public void setDossierAdministratif(DossierAdministratif dossierAdministratif) {
        this.dossierAdministratif = dossierAdministratif;
    }
}
