/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.controller;

/**
 *
 * @author Uyuki
 */
import com.biblioteca.biblioteca.service.LibroService;
import com.biblioteca.biblioteca.service.CategoriaService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        var libros = libroService.getAll();
        model.addAttribute("libros", libros);
        model.addAttribute("totalLibros", libros.size());
        model.addAttribute("categorias", categoriaService.getAll());
        model.addAttribute("libro", new com.biblioteca.biblioteca.domain.Libro());
        return "/libro/listado";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("libro") com.biblioteca.biblioteca.domain.Libro libro,
                          RedirectAttributes ra) {
        libroService.save(libro);
        ra.addFlashAttribute("msgOk", "Libro guardado.");
        return "redirect:/libro/listado";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable Long id, Model model, RedirectAttributes ra) {
        var opt = libroService.get(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("msgErr", "El libro no existe.");
            return "redirect:/libro/listado";
        }
        model.addAttribute("libro", opt.get());
        model.addAttribute("libros", libroService.getAll());
        model.addAttribute("totalLibros", libroService.getAll().size());
        model.addAttribute("categorias", categoriaService.getAll());
        return "/libro/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idLibro") Long idLibro, RedirectAttributes ra) {
        try {
            libroService.delete(idLibro);
            ra.addFlashAttribute("msgOk", "Libro eliminado.");
        } catch (DataIntegrityViolationException ex) {
            ra.addFlashAttribute("msgErr", "No se puede eliminar: datos relacionados.");
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("msgErr", "El libro no existe.");
        } catch (Exception ex) {
            ra.addFlashAttribute("msgErr", "Error inesperado al eliminar.");
        }
        return "redirect:/libro/listado";
    }
}