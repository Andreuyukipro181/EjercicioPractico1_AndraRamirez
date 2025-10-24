/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.controller;

/**
 *
 * @author Uyuki
 */
import com.biblioteca.biblioteca.domain.Libro;
import com.biblioteca.biblioteca.service.CategoriaService;
import com.biblioteca.biblioteca.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/libro")
public class LibroController {

    private final LibroService libroService;
    private final CategoriaService categoriaService;

    public LibroController(LibroService libroService, CategoriaService categoriaService) {
        this.libroService = libroService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var libros = libroService.getLibros(false);
        model.addAttribute("libros", libros);
        model.addAttribute("totalLibros", libros.size());
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute("libro", new Libro());
        return "libro/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Libro libro, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categorias", categoriaService.getCategorias(true));
            return "libro/listado";
        }
        libroService.save(libro);
        return "redirect:/libro/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") Integer id, Model model) {
        var l = libroService.getLibro(id).orElse(null);
        if (l == null) return "redirect:/libro/listado";
        model.addAttribute("libro", l);
        model.addAttribute("categorias", categoriaService.getCategorias(true));
        model.addAttribute("libros", libroService.getLibros(false));
        return "libro/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Integer idLibro) {
        libroService.delete(idLibro);
        return "redirect:/libro/listado";
    }
}
