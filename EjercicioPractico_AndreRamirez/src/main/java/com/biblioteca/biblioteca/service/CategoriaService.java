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
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaService {

    private final CategoriaRepository repo;

    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean soloActivas) {
        return soloActivas ? repo.findByActivoTrue() : repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Categoria> getCategoria(Integer idCategoria) {
        return repo.findById(idCategoria);
    }

    @Transactional
    public void save(Categoria c) {
        repo.save(c);
    }

    @Transactional
    public void delete(Integer idCategoria) {
        if (!repo.existsById(idCategoria)) {
            throw new IllegalArgumentException("La categoría no existe.");
        }
        repo.deleteById(idCategoria);
    }
}