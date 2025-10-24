/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.controller;

/**
 *
 * @author Uyuki
 */
import com.biblioteca.biblioteca.domain.Queja;
import com.biblioteca.biblioteca.service.QuejaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/queja")
public class QuejaController {

    private final QuejaService quejaService;

    public QuejaController(QuejaService quejaService) {
        this.quejaService = quejaService;
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("queja", new Queja());
        return "queja/form";
    }

    @PostMapping("/enviar")
    public String enviar(@Valid @ModelAttribute Queja queja, BindingResult br) {
        if (br.hasErrors()) {
            return "queja/form";
        }
        quejaService.save(queja);
        return "redirect:/";
    }
}
