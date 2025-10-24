package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.domain.Libro;
import com.biblioteca.biblioteca.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    private final LibroRepository repo;

    public LibroService(LibroRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void save(Libro l) {
        repo.save(l);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Transactional
    public Optional<Libro> get(Long id) {
        return repo.findById(id);
    }

    @Transactional
    public List<Libro> getAll() {
        return repo.findAll();
    }

  //  @Transactional(readOnly = true)
    public List<Libro> getDisponibles() {
        return repo.findByDisponibleTrue();
    }
}
