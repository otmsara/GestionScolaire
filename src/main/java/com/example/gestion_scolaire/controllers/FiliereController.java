package com.example.gestion_scolaire.controllers;

import org.springframework.ui.Model;
import com.example.gestion_scolaire.entities.Filiere;
import com.example.gestion_scolaire.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/filieres")
public class FiliereController {


    @Autowired
    private FiliereRepository filiereRepository;


    @GetMapping
    public String list(Model model) {
        model.addAttribute("filieres", filiereRepository.findAll());
        return "filieres/list";
    }


    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("filiere", new Filiere());
        return "filieres/form";
    }


    @PostMapping("/save")
    public String save(Filiere filiere) {
        filiereRepository.save(filiere);
        return "redirect:/filieres";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        filiereRepository.deleteById(id);
        return "redirect:/filieres";
    }
}
