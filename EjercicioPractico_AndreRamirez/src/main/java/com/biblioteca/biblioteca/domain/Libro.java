/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.biblioteca.biblioteca.domain;

/**
 *
 * @author Uyuki
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "libro")
public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 255)
    private String titulo;

    @NotBlank @Size(max = 200)
    private String autor;

    @Size(max = 20)
    private String isbn;

    @Lob
    private String descripcion; // TEXT

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "fecha_publicacion")
    private LocalDate fechaPublicacion;

    @NotNull
    private Boolean disponible = Boolean.TRUE;

    @Digits(integer = 8, fraction = 2)
    private BigDecimal precio;

    @Column(name = "created_at", insertable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private java.time.LocalDateTime updatedAt;
}