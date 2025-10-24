package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByDisponibleTrue();
}
