package com.example.gestion_scolaire.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Entity
public class DossierAdministratif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String numeroInscription;
    private LocalDate dateCreation;


    @OneToOne
    private Eleve eleve;
}
