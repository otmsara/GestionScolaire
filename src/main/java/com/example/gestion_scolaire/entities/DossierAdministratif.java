package com.example.gestion_scolaire.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


import java.time.LocalDate;

@Entity
public class DossierAdministratif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String numeroInscription;
    private LocalDate dateCreation;


    @OneToOne
    @JoinColumn(name = "eleve_id", nullable = false)
    private Eleve eleve;



    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

}
