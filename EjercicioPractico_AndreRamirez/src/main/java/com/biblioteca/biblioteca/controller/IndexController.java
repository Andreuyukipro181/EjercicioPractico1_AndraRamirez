package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.service.CategoriaService;
import com.biblioteca.biblioteca.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public IndexController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("libros",      libroService.getDisponibles());
        model.addAttribute("categorias",  categoriaService.getAll());
        return "general/index"; // busca /templates/general/index.html
    }
}
