/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.controller;

/**
 *
 * @author Uyuki
 */
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
        model.addAttribute("libros", libroService.getLibros(true));
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        return "index";
    }
}