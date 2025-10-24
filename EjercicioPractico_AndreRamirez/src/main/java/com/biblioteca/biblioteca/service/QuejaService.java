package com.biblioteca.biblioteca.service;

import com.biblioteca.biblioteca.domain.Queja;
import com.biblioteca.biblioteca.repository.QuejaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuejaService {

    private final QuejaRepository repo;

    public QuejaService(QuejaRepository repo) { this.repo = repo; }

    @Transactional
    public void save(Queja q) { repo.save(q); }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }

    @Transactional
    public Optional<Queja> get(Long id) { return repo.findById(id); }

    @Transactional
    public List<Queja> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
