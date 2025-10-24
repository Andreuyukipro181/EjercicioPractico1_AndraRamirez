package com.biblioteca.biblioteca.controller;

import com.biblioteca.biblioteca.domain.Queja;
import com.biblioteca.biblioteca.service.QuejaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/queja")
public class QuejaController {

    private final QuejaService quejaService;

    public QuejaController(QuejaService quejaService) {
        this.quejaService = quejaService;
    }

    @GetMapping("/form")
    public String form(Model model) {
        if (!model.containsAttribute("queja")) {
            model.addAttribute("queja", new Queja());
        }
        model.addAttribute("tipos", Queja.Tipo.values());
        return "queja/form";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("queja") Queja queja,
                          BindingResult br,
                          RedirectAttributes ra) {
        if (br.hasErrors()) {
            // Para que Thymeleaf recupere los errores después de redirect
            ra.addFlashAttribute("org.springframework.validation.BindingResult.queja", br);
            ra.addFlashAttribute("queja", queja);
            return "redirect:/queja/form";
        }
        quejaService.save(queja);
        ra.addFlashAttribute("ok", "¡Gracias! Tu mensaje fue enviado.");
        return "redirect:/queja/form";
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        model.addAttribute("quejas", quejaService.getAll());
        return "queja/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam Long id, RedirectAttributes ra) {
        quejaService.delete(id);
        ra.addFlashAttribute("ok", "Registro eliminado.");
        return "redirect:/queja/listado";
    }
}
