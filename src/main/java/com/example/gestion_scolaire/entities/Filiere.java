package com.example.gestion_scolaire.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String nom;


    @OneToMany(mappedBy = "filiere")
    private List<Eleve> eleves;


    @OneToMany(mappedBy = "filiere")
    private List<Cours> cours;
}