package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "queja")
public class Queja implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(max = 150)
    @Column(name = "nombre_cliente")
    private String nombre;

    @Email @Size(max = 200)
    @Column(name = "email")
    private String email;

    @Size(max = 30)
    @Column(name = "telefono")
    private String telefono;

    public enum Tipo { QUEJA, SUGERENCIA, CONSULTA }

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private Tipo tipo = Tipo.CONSULTA;

    @NotBlank @Size(max = 200)
    @Column(name = "asunto")
    private String asunto;

    @NotBlank
    @Lob
    @Column(name = "mensaje")
    private String mensaje;

    @Column(name = "tratado", nullable = false)
    private Boolean tratado = Boolean.FALSE;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime fecha;

    @PrePersist
    public void prePersist() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
        if (tratado == null) {
            tratado = Boolean.FALSE;
        }
        if (tipo == null) {
            tipo = Tipo.CONSULTA;
        }
    }
}
