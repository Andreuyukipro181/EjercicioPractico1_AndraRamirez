/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.service;

/**
 *
 * @author Uyuki
 */
import com.biblioteca.biblioteca.domain.Queja;

import com.biblioteca.biblioteca.repository.QuejaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuejaService {

    private final QuejaRepository repo;

    public QuejaService(QuejaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public void save(Queja q) {
        repo.save(q);
    }
}