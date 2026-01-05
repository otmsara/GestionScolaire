package com.example.gestion_scolaire.controllers;

import ch.qos.logback.core.model.Model;
import com.example.gestion_scolaire.repositories.EleveRepository;
import com.example.gestion_scolaire.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    // ENREGISTREMENT
    @PostMapping("/save")
    public String save(@ModelAttribute Eleve eleve) {
        eleveRepository.save(eleve);
        return "redirect:/eleves";
    }


    // SUPPRESSION
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        eleveRepository.deleteById(id);
        return "redirect:/eleves";
    }


    // MODIFICATION
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("eleve", eleveRepository.findById(id).get());
        model.addAttribute("filieres", filiereRepository.findAll());
        model.addAttribute("cours", coursRepository.findAll());
        return "eleves/form";
    }
}