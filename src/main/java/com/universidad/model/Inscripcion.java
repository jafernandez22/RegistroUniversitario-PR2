package com.universidad.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion", uniqueConstraints = @UniqueConstraint(columnNames = {"id_estudiante", "id_materia"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID único para la inscripción

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Estudiante estudiante; // Asociación con el estudiante (muchas inscripciones por estudiante)

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_materia", nullable = false)
    private Materia materia; // Asociación con la materia (muchas inscripciones por materia)

    @Column(name = "fecha_inscripcion", nullable = false)
    private LocalDate fechaInscripcion; // Fecha en que se inscribió el estudiante

    @Column(name = "estado", nullable = false, length = 20)
    private String estado; // Estado de la inscripción: Activo, Retirado, etc.
}
