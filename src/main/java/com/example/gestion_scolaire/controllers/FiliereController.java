package com.example.gestion_scolaire.controllers;

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
