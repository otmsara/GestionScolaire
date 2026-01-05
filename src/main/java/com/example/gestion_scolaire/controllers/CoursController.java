package com.example.gestion_scolaire.controllers;

import org.springframework.ui.Model;
import jakarta.validation.Valid;
import com.example.gestion_scolaire.entities.Cours;
import com.example.gestion_scolaire.repositories.CoursRepository;
import com.example.gestion_scolaire.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private FiliereRepository filiereRepository;

    // LISTE DES COURS
    @GetMapping
    public String list(Model model) {
        model.addAttribute("cours", coursRepository.findAll());
        return "cours/list";
    }

    // FORMULAIRE AJOUT
    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("filieres", filiereRepository.findAll());
        return "cours/form";
    }

    // ENREGISTREMENT (AJOUT / MODIFICATION) + VALIDATION
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Cours cours,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("filieres", filiereRepository.findAll());
            return "cours/form";
        }

        coursRepository.save(cours);
        return "redirect:/cours";
    }

    // MODIFICATION
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("cours", coursRepository.findById(id).get());
        model.addAttribute("filieres", filiereRepository.findAll());
        return "cours/form";
    }

    // SUPPRESSION
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        coursRepository.deleteById(id);
        return "redirect:/cours";
    }
}
