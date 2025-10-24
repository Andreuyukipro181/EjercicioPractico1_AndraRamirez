/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.controller;

/**
 *
 * @author Uyuki
 */
import com.biblioteca.biblioteca.domain.Categoria;
import com.biblioteca.biblioteca.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var categorias = service.getAll();
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        model.addAttribute("categoria", new Categoria());
        return "/categoria/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Categoria categoria, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categorias", service.getAll());
            return "/categoria/listado";
        }
        service.save(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model) {
        var cat = service.get(id).orElse(null);
        if (cat == null) return "redirect:/categoria/listado";
        model.addAttribute("categoria", cat);
        model.addAttribute("categorias", service.getAll());
        return "/categoria/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id) {
        service.delete(id);
        return "redirect:/categoria/listado";
    }
}