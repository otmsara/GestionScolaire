package com.example.gestion_scolaire.entities;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String intitule;


    @ManyToOne
    private Filiere filiere;


    @ManyToMany(mappedBy = "cours")
    private List<Eleve> eleves;
}
