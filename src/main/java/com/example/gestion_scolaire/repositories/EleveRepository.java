package com.example.gestion_scolaire.repositories;

import com.example.gestion_scolaire.entities.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EleveRepository extends JpaRepository<Eleve, Long> {}
