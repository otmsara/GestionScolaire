package com.example.gestion_scolaire.services;

import com.example.gestion_scolaire.entities.DossierAdministratif;
import com.example.gestion_scolaire.entities.Eleve;
import com.example.gestion_scolaire.repositories.EleveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EleveService {


    @Autowired
    private EleveRepository eleveRepository;


    public Eleve save(Eleve eleve) {
        DossierAdministratif dossier = new DossierAdministratif();
        dossier.setDateCreation(LocalDate.now());
        dossier.setEleve(eleve);
        eleve.setDossierAdministratif(dossier);
        return eleveRepository.save(eleve);
    }
}