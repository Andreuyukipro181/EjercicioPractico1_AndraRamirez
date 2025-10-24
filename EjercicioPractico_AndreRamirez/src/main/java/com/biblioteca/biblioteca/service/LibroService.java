/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.service;

/**
 *
 * @author Uyuki
 */
import com.biblioteca.biblioteca.domain.Libro;
import com.biblioteca.biblioteca.repository.LibroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    private final LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public List<Libro> getLibros(boolean soloActivos) {
        return soloActivos ? repo.findByActivoTrue() : repo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Libro> getLibro(Integer idLibro) {
        return repo.findById(idLibro);
    }

    @Transactional
    public void save(Libro l) {
        repo.save(l);
    }

    @Transactional
    public void delete(Integer idLibro) {
        if (!repo.existsById(idLibro)) {
            throw new IllegalArgumentException("El libro no existe.");
        }
        repo.deleteById(idLibro);
    }
}
