
package com.biblioteca.biblioteca.domain;

/**
 *
 * @author Uyuki
 */
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "queja")
public class Queja implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_queja")
    private Integer idQueja;

    @NotBlank @Size(max = 100)
    private String nombre;

    @NotBlank @Email @Size(max = 150)
    private String email;

    @NotBlank
    @Lob
    private String mensaje;

    @Column(columnDefinition = "timestamp")
    private LocalDateTime fecha; // Hibernate mapeará el default si no seteas, o puedes setear en el servicio.
}
