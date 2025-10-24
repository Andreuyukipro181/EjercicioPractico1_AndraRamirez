package com.biblioteca.biblioteca.repository;

import com.biblioteca.biblioteca.domain.Queja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuejaRepository extends JpaRepository<Queja, Long> {
}
