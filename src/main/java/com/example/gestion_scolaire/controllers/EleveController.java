package com.example.gestion_scolaire.controllers;

import com.example.gestion_scolaire.entities.Cours;
import com.example.gestion_scolaire.entities.DossierAdministratif;
import com.example.gestion_scolaire.entities.Eleve;
import com.example.gestion_scolaire.entities.Filiere;
import com.example.gestion_scolaire.repositories.CoursRepository;
import com.example.gestion_scolaire.repositories.EleveRepository;
import com.example.gestion_scolaire.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/eleves")
public class EleveController {

    @Autowired
    private EleveRepository eleveRepository;

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private CoursRepository coursRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("eleves", eleveRepository.findAll());
        return "eleves/list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("eleve", new Eleve());
        model.addAttribute("filieres", filiereRepository.findAll());
        model.addAttribute("cours", coursRepository.findAll());
        return "eleves/form";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Eleve eleve = eleveRepository.findById(id).orElse(new Eleve());
        model.addAttribute("eleve", eleve);
        model.addAttribute("filieres", filiereRepository.findAll());
        model.addAttribute("cours", coursRepository.findAll());
        return "eleves/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        eleveRepository.deleteById(id);
        return "redirect:/eleves";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Eleve eleve) {

        // Ensure Filiere
        if (eleve.getFiliere() != null && eleve.getFiliere().getId() != null) {
            Filiere filiere = filiereRepository.findById(eleve.getFiliere().getId())
                    .orElse(null);
            eleve.setFiliere(filiere);
        } else {
            eleve.setFiliere(null);
        }

        // Ensure selected courses
        List<Cours> selectedCours = new ArrayList<>();
        if (eleve.getCours() != null) {
            for (Cours c : eleve.getCours()) {
                coursRepository.findById(c.getId()).ifPresent(selectedCours::add);
            }
        }
        eleve.setCours(selectedCours);

        // **Ensure DossierAdministratif exists**
        if (eleve.getDossierAdministratif() == null) {
            DossierAdministratif dossier = new DossierAdministratif();
            dossier.setDateCreation(java.time.LocalDate.now());
            dossier.setEleve(eleve);
            eleve.setDossierAdministratif(dossier);
        }

        eleveRepository.save(eleve);
        return "redirect:/eleves";
    }


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Eleve eleve = eleveRepository.findById(id).orElse(null);
        if (eleve == null) {
            return "redirect:/eleves";
        }
        model.addAttribute("eleve", eleve);
        return "eleves/detail";
    }

}
