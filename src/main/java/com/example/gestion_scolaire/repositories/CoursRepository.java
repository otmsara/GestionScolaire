package com.example.gestion_scolaire.repositories;

import com.example.gestion_scolaire.entities.Cours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursRepository extends JpaRepository<Cours, Long> {}
