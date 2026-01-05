package com.example.gestion_scolaire.controllers;

import com.example.gestion_scolaire.entities.Cours;
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

    // LISTE DES ÉLÈVES
    @GetMapping
    public String list(Model model) {
        model.addAttribute("eleves", eleveRepository.findAll());
        return "eleves/list";
    }

    // FORMULAIRE AJOUT
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("eleve", new Eleve());
        model.addAttribute("filieres", filiereRepository.findAll());
        model.addAttribute("cours", coursRepository.findAll());
        return "eleves/form";
    }

    // MODIFICATION
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Eleve eleve = eleveRepository.findById(id).orElse(new Eleve());
        model.addAttribute("eleve", eleve);
        model.addAttribute("filieres", filiereRepository.findAll());
        model.addAttribute("cours", coursRepository.findAll());
        return "eleves/form";
    }

    // SUPPRESSION
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        eleveRepository.deleteById(id);
        return "redirect:/eleves";
    }

    // ENREGISTREMENT
    @PostMapping("/save")
    public String save(@ModelAttribute Eleve eleve) {

        // --- Résoudre la filière depuis l'ID ---
        if (eleve.getFiliere() != null && eleve.getFiliere().getId() != null) {
            Filiere filiere = filiereRepository.findById(eleve.getFiliere().getId())
                    .orElse(null);
            eleve.setFiliere(filiere);
        } else {
            eleve.setFiliere(null);
        }

        // --- Résoudre les cours sélectionnés depuis leurs IDs ---
        List<Cours> selectedCours = new ArrayList<>();
        if (eleve.getCours() != null) {
            for (Cours c : eleve.getCours()) {
                coursRepository.findById(c.getId()).ifPresent(selectedCours::add);
            }
        }
        eleve.setCours(selectedCours);

        eleveRepository.save(eleve);
        return "redirect:/eleves";
    }
}
