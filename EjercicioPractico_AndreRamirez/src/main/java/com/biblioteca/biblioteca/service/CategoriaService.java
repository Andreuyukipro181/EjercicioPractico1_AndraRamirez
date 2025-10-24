/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.service;

/**
 *
 * @author Uyuki
 */
import com.biblioteca.biblioteca.domain.Categoria;
import com.biblioteca.biblioteca.repository.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void save(Categoria c) { repo.save(c); }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }

    @Transactional
    public Optional<Categoria> get(Long id) { return repo.findById(id); }

    @Transactional
    public List<Categoria> getAll() { return repo.findAll(); }
}